package com.mojang.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import static com.mojang.serialization.SerializationUtils.unsafe;

class ClassSchemaBuilder {

    static final Map<Integer, ClassInfo> classInfoCache = new HashMap<>();
    static final Map<Class, SerializationUtils.Serializer> serializers = new HashMap<>(32);
    static final Map<Class, SerializationUtils.Deserializer> deserializers = new HashMap<>(32);
    static ClassInfo stringClassInfo;
    static ClassInfo stringBufferInfo;
    static ClassInfo stringBuilderInfo;

    public void build() {
        buildSerializers();
        buildDeserializers();

        Set<String> ignoreFields = new HashSet<>();
        ignoreFields.add("hash");

        stringClassInfo = createClassInfo(String.class, ignoreFields);
        stringBufferInfo = createClassInfo(StringBuffer.class, new HashSet<String>());
        stringBuilderInfo = createClassInfo(StringBuilder.class, new HashSet<String>());
    }

    private void buildSerializers() {
        serializers.put(int.class, new SerializationUtils.IntSerializer());
        serializers.put(int[].class, new SerializationUtils.IntArraySerializer());
        serializers.put(Integer.class, new SerializationUtils.IntegerSerializer());

        serializers.put(long.class, new SerializationUtils.LongSerializer());
        serializers.put(long[].class, new SerializationUtils.LongArraySerializer());
        serializers.put(Long.class, new SerializationUtils.LongWrapperSerializer());

        serializers.put(short.class, new SerializationUtils.ShortSerializer());
        serializers.put(short[].class, new SerializationUtils.ShortArraySerializer());
        serializers.put(Short.class, new SerializationUtils.ShortWrapperSerializer());

        serializers.put(byte.class, new SerializationUtils.ByteSerializer());
        serializers.put(byte[].class, new SerializationUtils.ByteArraySerializer());
        serializers.put(Byte.class, new SerializationUtils.ByteWrapperSerializer());

        serializers.put(char.class, new SerializationUtils.CharSerializer());
        serializers.put(char[].class, new SerializationUtils.CharArraySerializer());
        serializers.put(Character.class, new SerializationUtils.CharacterSerializer());

        serializers.put(boolean.class, new SerializationUtils.BooleanSerializer());
        serializers.put(boolean[].class, new SerializationUtils.BooleanArraySerializer());
        serializers.put(Boolean.class, new SerializationUtils.BooleanWrapperSerializer());

        serializers.put(float.class, new SerializationUtils.FloatSerializer());
        serializers.put(float[].class, new SerializationUtils.FloatArraySerializer());
        serializers.put(Float.class, new SerializationUtils.FloatWrapperSerializer());

        serializers.put(double.class, new SerializationUtils.DoubleSerializer());
        serializers.put(double[].class, new SerializationUtils.DoubleArraySerializer());
        serializers.put(Double.class, new SerializationUtils.DoubleWrapperSerializer());

        serializers.put(String.class, new SerializationUtils.StringSerializer());
        serializers.put(String[].class, new SerializationUtils.StringArraySerializer());
        serializers.put(StringBuffer.class, new SerializationUtils.StringBufferSerializer());
        serializers.put(StringBuilder.class, new SerializationUtils.StringBuilderSerializer());

        serializers.put(Enum.class, new SerializationUtils.EnumSerializer());
    }

    private void buildDeserializers() {
        deserializers.put(int.class, new SerializationUtils.IntDeserializer());
        deserializers.put(int[].class, new SerializationUtils.IntArrayDeserializer());
        deserializers.put(Integer.class, new SerializationUtils.IntegerDeserializer());

        deserializers.put(long.class, new SerializationUtils.LongDeserializer());
        deserializers.put(long[].class, new SerializationUtils.LongArrayDeserializer());
        deserializers.put(Long.class, new SerializationUtils.LongWrapperDeserializer());

        deserializers.put(short.class, new SerializationUtils.ShortDeserializer());
        deserializers.put(short[].class, new SerializationUtils.ShortArrayDeserializer());
        deserializers.put(Short.class, new SerializationUtils.ShortWrapperDeserializer());

        deserializers.put(byte.class, new SerializationUtils.ByteDeserializer());
        deserializers.put(byte[].class, new SerializationUtils.ByteArrayDeserializer());
        deserializers.put(Byte.class, new SerializationUtils.ByteWrapperDeserializer());

        deserializers.put(char.class, new SerializationUtils.CharDeserializer());
        deserializers.put(char[].class, new SerializationUtils.CharArrayDeserializer());
        deserializers.put(Character.class, new SerializationUtils.CharacterDeserializer());

        deserializers.put(boolean.class, new SerializationUtils.BooleanDeserializer());
        deserializers.put(boolean[].class, new SerializationUtils.BooleanArrayDeserializer());
        deserializers.put(Boolean.class, new SerializationUtils.BooleanWrapperDeserializer());

        deserializers.put(float.class, new SerializationUtils.FloatDeserializer());
        deserializers.put(float[].class, new SerializationUtils.FloatArrayDeserializer());
        deserializers.put(Float.class, new SerializationUtils.FloatWrapperDeserializer());

        deserializers.put(double.class, new SerializationUtils.DoubleDeserializer());
        deserializers.put(double[].class, new SerializationUtils.DoubleArrayDeserializer());
        deserializers.put(Double.class, new SerializationUtils.DoubleWrapperDeserializer());

        deserializers.put(String.class, new SerializationUtils.StringDeserializer());
        deserializers.put(String[].class, new SerializationUtils.StringArrayDeserializer());
        deserializers.put(StringBuffer.class, new SerializationUtils.StringBufferDeserializer());
        deserializers.put(StringBuilder.class, new SerializationUtils.StringBuilderDeserializer());

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
                long offset = SerializationUtils.unsafe.objectFieldOffset(field);
                if (serializers.containsKey(field.getType()) && deserializers.containsKey(field.getType())) {
                    fieldInfo = new FieldInfo(serializers.get(field.getType()), deserializers.get(field.getType()), offset);
                } else if (serializers.containsKey(field.getType().getSuperclass())) {
                    fieldInfo = new EnumFieldInfo(field.getType(), serializers.get(field.getType().getSuperclass()), offset);
                } else {
                    throw new UnknownRegisteredTypeException(field.getName());
                }
                fieldInfos[i] = fieldInfo;
            }
            sortFieldInfo(fieldInfos);
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

    /**
     *
     */
    static class FieldInfo {

        protected final SerializationUtils.Serializer fieldSerializer;
        protected final SerializationUtils.Deserializer fieldDeserializer;
        protected final long offset;

        FieldInfo(SerializationUtils.Serializer fieldSerializer, SerializationUtils.Deserializer fieldDeserializer, long offset) {
            this.fieldSerializer = fieldSerializer;
            this.fieldDeserializer = fieldDeserializer;
            this.offset = offset;
        }

        void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object) {
            fieldSerializer.serialize(unsafeMemory, object, offset);
        }

        void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object) {
            fieldDeserializer.deserialize(unsafeMemory, object, offset);
        }

    }

    /**
     *
     */
    final static class EnumFieldInfo extends FieldInfo {

        final Class enumClass;
        private Field ordinalField;
        private long ordinalOffset;
        private Field valuesField;
        private long valuesOffset;

        EnumFieldInfo(Class enumClass, SerializationUtils.Serializer fieldSerializer, long offset) {
            super(fieldSerializer, null, offset);
            this.enumClass = enumClass;
            getOrdinalField();
        }

        private void getOrdinalField() {
            try {
                ordinalField = enumClass.getSuperclass().getDeclaredField("ordinal");
                valuesField = enumClass.getDeclaredField("$VALUES");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Failed to get field from Enum Class:" + e.getMessage());
            }

            ordinalOffset = SerializationUtils.unsafe.objectFieldOffset(ordinalField);
            valuesOffset = SerializationUtils.unsafe.staticFieldOffset(valuesField);
        }

        void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object) {
            Object enumReference = SerializationUtils.unsafe.getObject(object, offset);
            fieldSerializer.serialize(unsafeMemory, enumReference, ordinalOffset);
        }

        void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object) {
            Object[] values = (Object[]) SerializationUtils.unsafe.getObject(enumClass, valuesOffset);
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
