package com.mojang.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.*;

import static com.mojang.serialization.SerializationUtils.unsafe;

class ClassSchemaBuilder {

    private static TypeRepository typeRepository = new TypeRepository();

    static final Map<Integer, ClassInfo> classInfoCache = new HashMap<>();

    static ClassInfo stringClassInfo;
    static ClassInfo stringBufferInfo;
    static ClassInfo stringBuilderInfo;
    static ClassInfo bigIntegerClassInfo;

    ClassSchemaBuilder() {
        Set<String> ignoreFields = new HashSet<>();
        ignoreFields.add("hash");

        stringClassInfo = createClassInfo(String.class, ignoreFields);
        stringBufferInfo = createClassInfo(StringBuffer.class, new HashSet<String>());
        stringBuilderInfo = createClassInfo(StringBuilder.class, new HashSet<String>());
        bigIntegerClassInfo = createClassInfo(BigInteger.class, new HashSet<String>());
    }

    void registerClass(Class c, Set<String> ignoreFields) {
        ClassInfo classInfo = classInfoCache.get(c);
        if (classInfo == null) {
            classInfo = createClassInfo(c, ignoreFields);
            int code = c.hashCode();
            classInfoCache.put(code, classInfo);
        }
    }

    ClassInfo createClassInfo(Class c, Set<String> ignoreFields) {
        ClassInfo classInfo = classInfoCache.get(c);
        if (classInfo == null) {
            List<Field> fields = getFieldsFor(c, ignoreFields);
            FieldInfo[] fieldInfos = new FieldInfo[fields.size()];
            for (int i = 0; i < fields.size(); i++) {
                FieldInfo fieldInfo;
                Field field = fields.get(i);
                if (typeRepository.serializers.containsKey(field.getType()) && typeRepository.deserializers.containsKey(field.getType())) {
                    fieldInfo = new FieldInfo(field, typeRepository.serializers.get(field.getType()), typeRepository.deserializers.get(field.getType()));
                } else if (typeRepository.serializers.containsKey(field.getType().getSuperclass())) {
                    fieldInfo = new EnumFieldInfo(field, typeRepository.serializers.get(field.getType().getSuperclass()));
                } else {
                    throw new UnknownRegisteredTypeException(field.getName());
                }
                fieldInfos[i] = fieldInfo;
            }
            sortFieldInfo(fieldInfos);
            fieldInfos = mergeFields(fieldInfos);
            classInfo = new ClassInfo(c, fieldInfos);
        }
        return classInfo;
    }

    private void sortFieldInfo(FieldInfo[] infos) {
        Arrays.sort(infos, new Comparator<FieldInfo>() {
            @Override
            public int compare(FieldInfo o1, FieldInfo o2) {
                return (int) (o1.offset - o2.offset);
            }
        });
    }

    private List<Field> getFieldsFor(Class c, Set<String> ignoreFields) {
        List<Field> fields = new ArrayList<>();
        List<Field> ffs = getAllFieldsRecursiveFor(c);
        for (Field f : ffs) {
            if (ignoreFields.contains(f.getName())) {
                continue;
            }
            if ((f.getModifiers() & Modifier.STATIC) == 0 && (f.getModifiers() & Modifier.TRANSIENT) == 0) {
                fields.add(f);
            }
        }
        return fields;
    }

    private List<Field> getAllFieldsRecursiveFor(Class c) {
        if (c == Object.class) {
            return new ArrayList<>();
        }
        List<Field> allFields = getAllFieldsRecursiveFor(c.getSuperclass());
        allFields.addAll(Arrays.asList(c.getDeclaredFields()));
        return allFields;
    }

    private FieldInfo[] mergeFields(FieldInfo[] fields) {
        if (fields.length == 1 || fields.length == 0) {
            return fields;
        }

        List<FieldInfo> result = new ArrayList<>();
        result.add(fields[0]);
        for (int i=1; i<fields.length; i++) {
            FieldInfo latestField = result.get(result.size() - 1);
            FieldInfo currentField = fields[i];
            if (latestField.isPrimitive() && currentField.isPrimitive()) {
                BatchFieldInfo batchField = latestField.merge(currentField);
                result.set(result.size() - 1, batchField);
            } else {
                result.add(currentField);
            }
        }

        return result.toArray(new FieldInfo[result.size()]);
    }

    /**
     *
     */
    static class FieldInfo {

        protected SerializationUtils.Serializer fieldSerializer;
        protected SerializationUtils.Deserializer fieldDeserializer;
        protected final Field field;
        protected long offset;

        FieldInfo(Field field, SerializationUtils.Serializer fieldSerializer, SerializationUtils.Deserializer fieldDeserializer) {
            this.field = field;
            this.fieldSerializer = fieldSerializer;
            this.fieldDeserializer = fieldDeserializer;
            this.offset = SerializationUtils.unsafe.objectFieldOffset(field);
        }

        void serialize(UnsafeMemory unsafeMemory, Object object) {
            fieldSerializer.serialize(unsafeMemory, object, offset);
        }

        void deserialize(UnsafeMemory unsafeMemory, Object object) {
            fieldDeserializer.deserialize(unsafeMemory, object, offset);
        }

        boolean isPrimitive() {
            return field.getType().isPrimitive();
        }

        public BatchFieldInfo merge(FieldInfo lastField) {
            int size = (int) (lastField.offset - this.offset + typeRepository.typeSizes.get(lastField.field.getType()));
            return new BatchFieldInfo(this, size);
        }
    }

    /**
     *
     */
    final static class BatchFieldInfo extends FieldInfo {

        private final FieldInfo firstField;

        BatchFieldInfo(FieldInfo firstField, int size) {
            super(firstField.field, new SerializationUtils.BatchSerializer(size), new SerializationUtils.BatchDeserializer(size));
            this.firstField = firstField;
        }

        public BatchFieldInfo merge(FieldInfo lastField) {
            int size = (int) (lastField.offset - this.offset + typeRepository.typeSizes.get(lastField.field.getType()));
            return new BatchFieldInfo(firstField, size);
        }

    }

    /**
     *
     */
    final static class EnumFieldInfo extends FieldInfo {

        private Field ordinalField;
        private long ordinalOffset;
        private Field valuesField;
        private long valuesOffset;

        EnumFieldInfo(Field field, SerializationUtils.Serializer fieldSerializer) {
            super(field, fieldSerializer, null);
            offset = SerializationUtils.unsafe.objectFieldOffset(field);
            getOrdinalField();
        }

        private void getOrdinalField() {
            try {
                ordinalField = field.getType().getSuperclass().getDeclaredField("ordinal");
                valuesField = field.getType().getDeclaredField("$VALUES");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Failed to get field from Enum Class:" + e.getMessage());
            }

            ordinalOffset = SerializationUtils.unsafe.objectFieldOffset(ordinalField);
            valuesOffset = SerializationUtils.unsafe.staticFieldOffset(valuesField);
        }

        void serialize(UnsafeMemory unsafeMemory, Object object) {
            Object enumReference = SerializationUtils.unsafe.getObject(object, offset);
            fieldSerializer.serialize(unsafeMemory, enumReference, ordinalOffset);
        }

        void deserialize(UnsafeMemory unsafeMemory, Object object) {
            Object[] values = (Object[]) SerializationUtils.unsafe.getObject(field.getType(), valuesOffset);
            int ordinal = unsafeMemory.readInt();
            SerializationUtils.unsafe.putObject(object, offset, values[ordinal]);
        }
    }

    /**
     *
     */
    final static class ClassInfo {
        final Class<?> clazz;
        final FieldInfo[] fieldInfos;

        ClassInfo(Class<?> clazz, FieldInfo[] fieldInfos) {
            this.clazz = clazz;
            this.fieldInfos = fieldInfos;
        }

        Object instance() throws InstantiationException {
            return unsafe.allocateInstance(clazz);
        }
    }
}
