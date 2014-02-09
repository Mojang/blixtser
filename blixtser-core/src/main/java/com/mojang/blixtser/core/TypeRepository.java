package com.mojang.blixtser.core;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static com.mojang.blixtser.core.SerializationUtils.*;

public abstract class TypeRepository {

    static final TypeRepository volatileTypeRepository = new VolatileTypeRepository();
    static final TypeRepository nonVolatileTypeRepository = new NonVolatileTypeRepository();

    private static final Map<Class, Integer> typeSizes = new HashMap<>(8);

    static {
        typeSizes.put(long.class, 8);
        typeSizes.put(double.class, 8);
        typeSizes.put(int.class, 4);
        typeSizes.put(float.class, 4);
        typeSizes.put(char.class, 2);
        typeSizes.put(short.class, 2);
        typeSizes.put(boolean.class, 1);
        typeSizes.put(byte.class, 1);
    }

    static long sizeOf(Class<?> type) {
        return typeSizes.get(type);
    }

    public abstract Serializer getSerializer(Class<?> type);

    public abstract Deserializer getDeserializer(Class<?> type);

    public abstract boolean serializerDeserializerExistsFor(Class<?> type);


    /**
     *
     */
    static class NonVolatileTypeRepository extends TypeRepository {

        private final Map<Class, Serializer> serializers = new HashMap<>(32);
        private final Map<Class, Deserializer> deserializers = new HashMap<>(32);

        NonVolatileTypeRepository() {
            buildSerializers();
            buildDeserializers();
        }

        public Serializer getSerializer(Class<?> type) {
            return serializers.get(type);
        }

        public Deserializer getDeserializer(Class<?> type) {
            return deserializers.get(type);
        }

        public boolean serializerDeserializerExistsFor(Class<?> type) {
            return serializers.containsKey(type) && deserializers.containsKey(type);
        }

        private void buildSerializers() {
            serializers.put(int.class, new SerializationUtils.IntSerializer());
            serializers.put(int[].class, new SerializationUtils.IntArraySerializer());
            serializers.put(Integer.class, new SerializationUtils.IntegerSerializer());
            serializers.put(BigInteger.class, new SerializationUtils.BigIntegerSerializer());

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
            deserializers.put(BigInteger.class, new SerializationUtils.BigIntegerDeserializer());

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


    }

    /**
     *
     */
    static class VolatileTypeRepository extends TypeRepository {

        private final Map<Class, Serializer> serializers = new HashMap<>(32);
        private final Map<Class, Deserializer> deserializers = new HashMap<>(32);

        VolatileTypeRepository() {
            buildVolatileSerializers();
            buildVolatileDeserializers();
        }

        @Override
        public Serializer getSerializer(Class<?> type) {
            return serializers.get(type);
        }

        @Override
        public Deserializer getDeserializer(Class<?> type) {
            return deserializers.get(type);
        }

        @Override
        public boolean serializerDeserializerExistsFor(Class<?> type) {
            return serializers.containsKey(type) && deserializers.containsKey(type);
        }

        private void buildVolatileSerializers() {
            serializers.put(int.class, new SerializationUtils.IntVolatileSerializer());
            serializers.put(int[].class, new SerializationUtils.IntArrayVolatileSerializer());
            serializers.put(Integer.class, new SerializationUtils.IntegerVolatileSerializer());
            serializers.put(BigInteger.class, new SerializationUtils.BigIntegerVolatileSerializer());

            serializers.put(long.class, new SerializationUtils.LongVolatileSerializer());
            serializers.put(long[].class, new SerializationUtils.LongArrayVolatileSerializer());
            serializers.put(Long.class, new SerializationUtils.LongWrapperVolatileSerializer());

            serializers.put(short.class, new SerializationUtils.ShortVolatileSerializer());
            serializers.put(short[].class, new SerializationUtils.ShortArrayVolatileSerializer());
            serializers.put(Short.class, new SerializationUtils.ShortWrapperVolatileSerializer());

            serializers.put(byte.class, new SerializationUtils.ByteVolatileSerializer());
            serializers.put(byte[].class, new SerializationUtils.ByteArrayVolatileSerializer());
            serializers.put(Byte.class, new SerializationUtils.ByteWrapperVolatileSerializer());

            serializers.put(char.class, new SerializationUtils.CharVolatileSerializer());
            serializers.put(char[].class, new SerializationUtils.CharArrayVolatileSerializer());
            serializers.put(Character.class, new SerializationUtils.CharacterVolatileSerializer());

            serializers.put(boolean.class, new SerializationUtils.BooleanVolatileSerializer());
            serializers.put(boolean[].class, new SerializationUtils.BooleanArrayVolatileSerializer());
            serializers.put(Boolean.class, new SerializationUtils.BooleanWrapperVolatileSerializer());

            serializers.put(float.class, new SerializationUtils.FloatVolatileSerializer());
            serializers.put(float[].class, new SerializationUtils.FloatArrayVolatileSerializer());
            serializers.put(Float.class, new SerializationUtils.FloatWrapperVolatileSerializer());

            serializers.put(double.class, new SerializationUtils.DoubleVolatileSerializer());
            serializers.put(double[].class, new SerializationUtils.DoubleArrayVolatileSerializer());
            serializers.put(Double.class, new SerializationUtils.DoubleWrapperVolatileSerializer());

            serializers.put(String.class, new SerializationUtils.StringVolatileSerializer());
            serializers.put(String[].class, new SerializationUtils.StringArrayVolatileSerializer());
            serializers.put(StringBuffer.class, new SerializationUtils.StringBufferVolatileSerializer());
            serializers.put(StringBuilder.class, new SerializationUtils.StringBuilderVolatileSerializer());

            serializers.put(Enum.class, new SerializationUtils.EnumVolatileSerializer());
        }

        private void buildVolatileDeserializers() {
            deserializers.put(int.class, new SerializationUtils.IntVolatileDeserializer());
            deserializers.put(int[].class, new SerializationUtils.IntArrayVolatileDeserializer());
            deserializers.put(Integer.class, new SerializationUtils.IntegerVolatileDeserializer());
            deserializers.put(BigInteger.class, new SerializationUtils.BigIntegerVolatileDeserializer());

            deserializers.put(long.class, new SerializationUtils.LongVolatileDeserializer());
            deserializers.put(long[].class, new SerializationUtils.LongArrayVolatileDeserializer());
            deserializers.put(Long.class, new SerializationUtils.LongWrapperVolatileDeserializer());

            deserializers.put(short.class, new SerializationUtils.ShortVolatileDeserializer());
            deserializers.put(short[].class, new SerializationUtils.ShortArrayVolatileDeserializer());
            deserializers.put(Short.class, new SerializationUtils.ShortWrapperVolatileDeserializer());

            deserializers.put(byte.class, new SerializationUtils.ByteVolatileDeserializer());
            deserializers.put(byte[].class, new SerializationUtils.ByteArrayVolatileDeserializer());
            deserializers.put(Byte.class, new SerializationUtils.ByteWrapperVolatileDeserializer());

            deserializers.put(char.class, new SerializationUtils.CharVolatileDeserializer());
            deserializers.put(char[].class, new SerializationUtils.CharArrayVolatileDeserializer());
            deserializers.put(Character.class, new SerializationUtils.CharacterVolatileDeserializer());

            deserializers.put(boolean.class, new SerializationUtils.BooleanVolatileDeserializer());
            deserializers.put(boolean[].class, new SerializationUtils.BooleanArrayVolatileDeserializer());
            deserializers.put(Boolean.class, new SerializationUtils.BooleanWrapperVolatileDeserializer());

            deserializers.put(float.class, new SerializationUtils.FloatVolatileDeserializer());
            deserializers.put(float[].class, new SerializationUtils.FloatArrayVolatileDeserializer());
            deserializers.put(Float.class, new SerializationUtils.FloatWrapperVolatileDeserializer());

            deserializers.put(double.class, new SerializationUtils.DoubleVolatileDeserializer());
            deserializers.put(double[].class, new SerializationUtils.DoubleArrayVolatileDeserializer());
            deserializers.put(Double.class, new SerializationUtils.DoubleWrapperVolatileDeserializer());

            deserializers.put(String.class, new SerializationUtils.StringVolatileDeserializer());
            deserializers.put(String[].class, new SerializationUtils.StringArrayVolatileDeserializer());
            deserializers.put(StringBuffer.class, new SerializationUtils.StringBufferVolatileDeserializer());
            deserializers.put(StringBuilder.class, new SerializationUtils.StringBuilderVolatileDeserializer());

        }

    }



}
