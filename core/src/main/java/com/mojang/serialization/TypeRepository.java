package com.mojang.serialization;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TypeRepository {

    final Map<Class, SerializationUtils.Serializer> serializers = new HashMap<>(32);
    final Map<Class, SerializationUtils.Deserializer> deserializers = new HashMap<>(32);

    final Map<Class, SerializationUtils.Serializer> volatileSerializers = new HashMap<>(32);
    final Map<Class, SerializationUtils.Deserializer> volatileDeserializers = new HashMap<>(32);

    final Map<Class, Integer> typeSizes = new HashMap<>(8);


    public TypeRepository() {
        buildSerializers();
        buildDeserializers();

        buildVolatileSerializers();
        buildVolatileDeserializers();

        buildPrimitiveTypeSizes();
    }

    private void buildPrimitiveTypeSizes() {
        typeSizes.put(long.class, 8);
        typeSizes.put(double.class, 8);
        typeSizes.put(int.class, 4);
        typeSizes.put(float.class, 4);
        typeSizes.put(char.class, 2);
        typeSizes.put(short.class, 2);
        typeSizes.put(boolean.class, 1);
        typeSizes.put(byte.class, 1);
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

    private void buildVolatileSerializers() {
        volatileSerializers.put(int.class, new SerializationUtils.IntVolatileSerializer());
        volatileSerializers.put(int[].class, new SerializationUtils.IntArrayVolatileSerializer());
        volatileSerializers.put(Integer.class, new SerializationUtils.IntegerVolatileSerializer());
        volatileSerializers.put(BigInteger.class, new SerializationUtils.BigIntegerVolatileSerializer());

        volatileSerializers.put(long.class, new SerializationUtils.LongVolatileSerializer());
        volatileSerializers.put(long[].class, new SerializationUtils.LongArrayVolatileSerializer());
        volatileSerializers.put(Long.class, new SerializationUtils.LongWrapperVolatileSerializer());

        volatileSerializers.put(short.class, new SerializationUtils.ShortVolatileSerializer());
        volatileSerializers.put(short[].class, new SerializationUtils.ShortArrayVolatileSerializer());
        volatileSerializers.put(Short.class, new SerializationUtils.ShortWrapperVolatileSerializer());

        volatileSerializers.put(byte.class, new SerializationUtils.ByteVolatileSerializer());
        volatileSerializers.put(byte[].class, new SerializationUtils.ByteArrayVolatileSerializer());
        volatileSerializers.put(Byte.class, new SerializationUtils.ByteWrapperVolatileSerializer());

        volatileSerializers.put(char.class, new SerializationUtils.CharVolatileSerializer());
        volatileSerializers.put(char[].class, new SerializationUtils.CharArrayVolatileSerializer());
        volatileSerializers.put(Character.class, new SerializationUtils.CharacterVolatileSerializer());

        volatileSerializers.put(boolean.class, new SerializationUtils.BooleanVolatileSerializer());
        volatileSerializers.put(boolean[].class, new SerializationUtils.BooleanArrayVolatileSerializer());
        volatileSerializers.put(Boolean.class, new SerializationUtils.BooleanWrapperVolatileSerializer());

        volatileSerializers.put(float.class, new SerializationUtils.FloatVolatileSerializer());
        volatileSerializers.put(float[].class, new SerializationUtils.FloatArrayVolatileSerializer());
        volatileSerializers.put(Float.class, new SerializationUtils.FloatWrapperVolatileSerializer());

        volatileSerializers.put(double.class, new SerializationUtils.DoubleVolatileSerializer());
        volatileSerializers.put(double[].class, new SerializationUtils.DoubleArrayVolatileSerializer());
        volatileSerializers.put(Double.class, new SerializationUtils.DoubleWrapperVolatileSerializer());

        volatileSerializers.put(String.class, new SerializationUtils.StringVolatileSerializer());
        volatileSerializers.put(String[].class, new SerializationUtils.StringArrayVolatileSerializer());
        volatileSerializers.put(StringBuffer.class, new SerializationUtils.StringBufferVolatileSerializer());
        volatileSerializers.put(StringBuilder.class, new SerializationUtils.StringBuilderVolatileSerializer());

        volatileSerializers.put(Enum.class, new SerializationUtils.EnumVolatileSerializer());
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

    private void buildVolatileDeserializers() {
        volatileDeserializers.put(int.class, new SerializationUtils.IntVolatileDeserializer());
        volatileDeserializers.put(int[].class, new SerializationUtils.IntArrayVolatileDeserializer());
        volatileDeserializers.put(Integer.class, new SerializationUtils.IntegerVolatileDeserializer());
        volatileDeserializers.put(BigInteger.class, new SerializationUtils.BigIntegerVolatileDeserializer());

        volatileDeserializers.put(long.class, new SerializationUtils.LongVolatileDeserializer());
        volatileDeserializers.put(long[].class, new SerializationUtils.LongArrayVolatileDeserializer());
        volatileDeserializers.put(Long.class, new SerializationUtils.LongWrapperVolatileDeserializer());

        volatileDeserializers.put(short.class, new SerializationUtils.ShortVolatileDeserializer());
        volatileDeserializers.put(short[].class, new SerializationUtils.ShortArrayVolatileDeserializer());
        volatileDeserializers.put(Short.class, new SerializationUtils.ShortWrapperVolatileDeserializer());

        volatileDeserializers.put(byte.class, new SerializationUtils.ByteVolatileDeserializer());
        volatileDeserializers.put(byte[].class, new SerializationUtils.ByteArrayVolatileDeserializer());
        volatileDeserializers.put(Byte.class, new SerializationUtils.ByteWrapperVolatileDeserializer());

        volatileDeserializers.put(char.class, new SerializationUtils.CharVolatileDeserializer());
        volatileDeserializers.put(char[].class, new SerializationUtils.CharArrayVolatileDeserializer());
        volatileDeserializers.put(Character.class, new SerializationUtils.CharacterVolatileDeserializer());

        volatileDeserializers.put(boolean.class, new SerializationUtils.BooleanVolatileDeserializer());
        volatileDeserializers.put(boolean[].class, new SerializationUtils.BooleanArrayVolatileDeserializer());
        volatileDeserializers.put(Boolean.class, new SerializationUtils.BooleanWrapperVolatileDeserializer());

        volatileDeserializers.put(float.class, new SerializationUtils.FloatVolatileDeserializer());
        volatileDeserializers.put(float[].class, new SerializationUtils.FloatArrayVolatileDeserializer());
        volatileDeserializers.put(Float.class, new SerializationUtils.FloatWrapperVolatileDeserializer());

        volatileDeserializers.put(double.class, new SerializationUtils.DoubleVolatileDeserializer());
        volatileDeserializers.put(double[].class, new SerializationUtils.DoubleArrayVolatileDeserializer());
        volatileDeserializers.put(Double.class, new SerializationUtils.DoubleWrapperVolatileDeserializer());

        volatileDeserializers.put(String.class, new SerializationUtils.StringVolatileDeserializer());
        volatileDeserializers.put(String[].class, new SerializationUtils.StringArrayVolatileDeserializer());
        volatileDeserializers.put(StringBuffer.class, new SerializationUtils.StringBufferVolatileDeserializer());
        volatileDeserializers.put(StringBuilder.class, new SerializationUtils.StringBuilderVolatileDeserializer());

    }


}
