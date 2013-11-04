package com.mojang.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class ClassSchemaBuilder {

    static final Map<Integer, ClassInfo> classInfoCache = new HashMap<>();
    static ClassInfo stringClassInfo;
    static final Map<Class, SerializationUtils.Serializable> typeRegistry = new HashMap<>(32);

    public void build() {
        typeRegistry.put(int.class, new SerializationUtils.IntSerializer());
        typeRegistry.put(int[].class, new SerializationUtils.IntArraySerializer());
        typeRegistry.put(Integer.class, new SerializationUtils.IntegerSerializer());

        typeRegistry.put(long.class, new SerializationUtils.LongSerializer());
        typeRegistry.put(long[].class, new SerializationUtils.LongArraySerializer());
        typeRegistry.put(Long.class, new SerializationUtils.LongWrapperSerializer());

        typeRegistry.put(short.class, new SerializationUtils.ShortSerializer());
        typeRegistry.put(short[].class, new SerializationUtils.ShortArraySerializer());
        typeRegistry.put(Short.class, new SerializationUtils.ShortWrapperSerializer());

        typeRegistry.put(byte.class, new SerializationUtils.ByteSerializer());
        typeRegistry.put(byte[].class, new SerializationUtils.ByteArraySerializer());
        typeRegistry.put(Byte.class, new SerializationUtils.ByteWrapperSerializer());

        typeRegistry.put(char.class, new SerializationUtils.CharSerializer());
        typeRegistry.put(char[].class, new SerializationUtils.CharArraySerializer());
        typeRegistry.put(Character.class, new SerializationUtils.CharacterSerializer());

        typeRegistry.put(boolean.class, new SerializationUtils.BooleanSerializer());
        typeRegistry.put(boolean[].class, new SerializationUtils.BooleanArraySerializer());
        typeRegistry.put(Boolean.class, new SerializationUtils.BooleanWrapperSerializer());

        typeRegistry.put(float.class, new SerializationUtils.FloatSerializer());
        typeRegistry.put(float[].class, new SerializationUtils.FloatArraySerializer());
        typeRegistry.put(Float.class, new SerializationUtils.FloatWrapperSerializer());

        typeRegistry.put(double.class, new SerializationUtils.DoubleSerializer());
        typeRegistry.put(double[].class, new SerializationUtils.DoubleArraySerializer());
        typeRegistry.put(Double.class, new SerializationUtils.DoubleWrapperSerializer());

        typeRegistry.put(String.class, new SerializationUtils.StringSerializer());
        typeRegistry.put(String[].class, new SerializationUtils.StringArraySerializer());

        typeRegistry.put(Object[].class, new SerializationUtils.ObjectArraySerializer());


        stringClassInfo = registerClass(String.class);

        registerClass(ArrayList.class, true);

    }

    ClassInfo registerClass(Class c) {
        return registerClass(c, false);
    }

    ClassInfo registerClass(Class c, boolean includingTransientFields) {
        ClassInfo classInfo = classInfoCache.get(c);
        if (classInfo == null) {
            int code = c.hashCode();
            List<Field> fields = getFieldsFor(c, includingTransientFields);
            FieldInfo[] fieldInfos = new FieldInfo[fields.size()];
            for (int i = 0; i < fields.size(); i++) {
                FieldInfo fieldInfo = new FieldInfo(typeRegistry.get(fields.get(i).getType()), SerializationUtils.unsafe.objectFieldOffset(fields.get(i)));
                fieldInfos[i] = fieldInfo;
            }
            sortFieldInfo(fieldInfos);
            classInfo = new ClassInfo(c, fieldInfos);
            classInfoCache.put(code, classInfo);
        }
        return classInfo;
    }

    public static void main(String[] args) {
        ClassSchemaBuilder csb = new ClassSchemaBuilder();
        csb.build();
    }

    private void sortFieldInfo(FieldInfo[] infos) {
        Arrays.sort(infos, new Comparator<FieldInfo>() {
            @Override
            public int compare(FieldInfo o1, FieldInfo o2) {
                return (int) (o1.offset - o2.offset);
            }
        });
    }

    private List<Field> getFieldsFor(Class c, boolean includingTransientFields) {
        List<Field> fields = new ArrayList<>();
        Field[] ffs = c.getDeclaredFields();
        for (Field f : ffs) {
            if ((f.getModifiers() & Modifier.STATIC) == 0 &&
                    (includingTransientFields || (f.getModifiers() & Modifier.TRANSIENT) == 0)) {
                fields.add(f);
            }
        }
        return fields;
    }

    /**
     *
     */
    static class FieldInfo {
        final SerializationUtils.Serializable fieldSerializer;
        final long offset;

        FieldInfo(SerializationUtils.Serializable fieldSerializer, long offset) {
            this.fieldSerializer = fieldSerializer;
            this.offset = offset;
        }
    }

    /**
     *
     */
    static class ClassInfo {
        final Class<?> clazz;
        final FieldInfo[] fieldInfos;

        ClassInfo(Class<?> clazz, FieldInfo[] fieldInfos) {
            this.clazz = clazz;
            this.fieldInfos = fieldInfos;
        }
    }



}
