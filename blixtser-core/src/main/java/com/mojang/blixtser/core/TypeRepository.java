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
            serializers.put(int.class, new IntSerializer());
            serializers.put(int[].class, new IntArraySerializer());
            serializers.put(int[][].class, new Int2DArraySerializer());
            serializers.put(Integer.class, new IntegerSerializer());
            serializers.put(BigInteger.class, new BigIntegerSerializer());

            serializers.put(long.class, new LongSerializer());
            serializers.put(long[].class, new LongArraySerializer());
            serializers.put(Long.class, new LongWrapperSerializer());

            serializers.put(short.class, new ShortSerializer());
            serializers.put(short[].class, new ShortArraySerializer());
            serializers.put(Short.class, new ShortWrapperSerializer());

            serializers.put(byte.class, new ByteSerializer());
            serializers.put(byte[].class, new ByteArraySerializer());
            serializers.put(Byte.class, new ByteWrapperSerializer());

            serializers.put(char.class, new CharSerializer());
            serializers.put(char[].class, new CharArraySerializer());
            serializers.put(Character.class, new CharacterSerializer());

            serializers.put(boolean.class, new BooleanSerializer());
            serializers.put(boolean[].class, new BooleanArraySerializer());
            serializers.put(Boolean.class, new BooleanWrapperSerializer());

            serializers.put(float.class, new FloatSerializer());
            serializers.put(float[].class, new FloatArraySerializer());
            serializers.put(Float.class, new FloatWrapperSerializer());

            serializers.put(double.class, new DoubleSerializer());
            serializers.put(double[].class, new DoubleArraySerializer());
            serializers.put(Double.class, new DoubleWrapperSerializer());

            serializers.put(String.class, new StringSerializer());
            serializers.put(String[].class, new StringArraySerializer());
            serializers.put(StringBuffer.class, new StringBufferSerializer());
            serializers.put(StringBuilder.class, new StringBuilderSerializer());

            serializers.put(Enum.class, new EnumSerializer());
        }


        private void buildDeserializers() {
            deserializers.put(int.class, new IntDeserializer());
            deserializers.put(int[].class, new IntArrayDeserializer());
            deserializers.put(int[][].class, new Int2DArrayDeserializer());
            deserializers.put(Integer.class, new IntegerDeserializer());
            deserializers.put(BigInteger.class, new BigIntegerDeserializer());

            deserializers.put(long.class, new LongDeserializer());
            deserializers.put(long[].class, new LongArrayDeserializer());
            deserializers.put(Long.class, new LongWrapperDeserializer());

            deserializers.put(short.class, new ShortDeserializer());
            deserializers.put(short[].class, new ShortArrayDeserializer());
            deserializers.put(Short.class, new ShortWrapperDeserializer());

            deserializers.put(byte.class, new ByteDeserializer());
            deserializers.put(byte[].class, new ByteArrayDeserializer());
            deserializers.put(Byte.class, new ByteWrapperDeserializer());

            deserializers.put(char.class, new CharDeserializer());
            deserializers.put(char[].class, new CharArrayDeserializer());
            deserializers.put(Character.class, new CharacterDeserializer());

            deserializers.put(boolean.class, new BooleanDeserializer());
            deserializers.put(boolean[].class, new BooleanArrayDeserializer());
            deserializers.put(Boolean.class, new BooleanWrapperDeserializer());

            deserializers.put(float.class, new FloatDeserializer());
            deserializers.put(float[].class, new FloatArrayDeserializer());
            deserializers.put(Float.class, new FloatWrapperDeserializer());

            deserializers.put(double.class, new DoubleDeserializer());
            deserializers.put(double[].class, new DoubleArrayDeserializer());
            deserializers.put(Double.class, new DoubleWrapperDeserializer());

            deserializers.put(String.class, new StringDeserializer());
            deserializers.put(String[].class, new StringArrayDeserializer());
            deserializers.put(StringBuffer.class, new StringBufferDeserializer());
            deserializers.put(StringBuilder.class, new StringBuilderDeserializer());

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
            serializers.put(int.class, new IntVolatileSerializer());
            serializers.put(int[].class, new IntArrayVolatileSerializer());
            serializers.put(Integer.class, new IntegerVolatileSerializer());
            serializers.put(BigInteger.class, new BigIntegerVolatileSerializer());

            serializers.put(long.class, new LongVolatileSerializer());
            serializers.put(long[].class, new LongArrayVolatileSerializer());
            serializers.put(Long.class, new LongWrapperVolatileSerializer());

            serializers.put(short.class, new ShortVolatileSerializer());
            serializers.put(short[].class, new ShortArrayVolatileSerializer());
            serializers.put(Short.class, new ShortWrapperVolatileSerializer());

            serializers.put(byte.class, new ByteVolatileSerializer());
            serializers.put(byte[].class, new ByteArrayVolatileSerializer());
            serializers.put(Byte.class, new ByteWrapperVolatileSerializer());

            serializers.put(char.class, new CharVolatileSerializer());
            serializers.put(char[].class, new CharArrayVolatileSerializer());
            serializers.put(Character.class, new CharacterVolatileSerializer());

            serializers.put(boolean.class, new BooleanVolatileSerializer());
            serializers.put(boolean[].class, new BooleanArrayVolatileSerializer());
            serializers.put(Boolean.class, new BooleanWrapperVolatileSerializer());

            serializers.put(float.class, new FloatVolatileSerializer());
            serializers.put(float[].class, new FloatArrayVolatileSerializer());
            serializers.put(Float.class, new FloatWrapperVolatileSerializer());

            serializers.put(double.class, new DoubleVolatileSerializer());
            serializers.put(double[].class, new DoubleArrayVolatileSerializer());
            serializers.put(Double.class, new DoubleWrapperVolatileSerializer());

            serializers.put(String.class, new StringVolatileSerializer());
            serializers.put(String[].class, new StringArrayVolatileSerializer());
            serializers.put(StringBuffer.class, new StringBufferVolatileSerializer());
            serializers.put(StringBuilder.class, new StringBuilderVolatileSerializer());

            serializers.put(Enum.class, new EnumVolatileSerializer());
        }

        private void buildVolatileDeserializers() {
            deserializers.put(int.class, new IntVolatileDeserializer());
            deserializers.put(int[].class, new IntArrayVolatileDeserializer());
            deserializers.put(Integer.class, new IntegerVolatileDeserializer());
            deserializers.put(BigInteger.class, new BigIntegerVolatileDeserializer());

            deserializers.put(long.class, new LongVolatileDeserializer());
            deserializers.put(long[].class, new LongArrayVolatileDeserializer());
            deserializers.put(Long.class, new LongWrapperVolatileDeserializer());

            deserializers.put(short.class, new ShortVolatileDeserializer());
            deserializers.put(short[].class, new ShortArrayVolatileDeserializer());
            deserializers.put(Short.class, new ShortWrapperVolatileDeserializer());

            deserializers.put(byte.class, new ByteVolatileDeserializer());
            deserializers.put(byte[].class, new ByteArrayVolatileDeserializer());
            deserializers.put(Byte.class, new ByteWrapperVolatileDeserializer());

            deserializers.put(char.class, new CharVolatileDeserializer());
            deserializers.put(char[].class, new CharArrayVolatileDeserializer());
            deserializers.put(Character.class, new CharacterVolatileDeserializer());

            deserializers.put(boolean.class, new BooleanVolatileDeserializer());
            deserializers.put(boolean[].class, new BooleanArrayVolatileDeserializer());
            deserializers.put(Boolean.class, new BooleanWrapperVolatileDeserializer());

            deserializers.put(float.class, new FloatVolatileDeserializer());
            deserializers.put(float[].class, new FloatArrayVolatileDeserializer());
            deserializers.put(Float.class, new FloatWrapperVolatileDeserializer());

            deserializers.put(double.class, new DoubleVolatileDeserializer());
            deserializers.put(double[].class, new DoubleArrayVolatileDeserializer());
            deserializers.put(Double.class, new DoubleWrapperVolatileDeserializer());

            deserializers.put(String.class, new StringVolatileDeserializer());
            deserializers.put(String[].class, new StringArrayVolatileDeserializer());
            deserializers.put(StringBuffer.class, new StringBufferVolatileDeserializer());
            deserializers.put(StringBuilder.class, new StringBuilderVolatileDeserializer());

        }

    }



}
