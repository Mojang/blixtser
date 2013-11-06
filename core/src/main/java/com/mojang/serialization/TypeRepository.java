package com.mojang.serialization;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TypeRepository {

    final Map<Class, SerializationUtils.Serializer> serializers = new HashMap<>(32);
    final Map<Class, SerializationUtils.Deserializer> deserializers = new HashMap<>(32);

    public TypeRepository() {
        buildSerializers();
        buildDeserializers();
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
