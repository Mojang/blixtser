package com.mojang.blixtser.core;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static com.mojang.blixtser.core.ClassSchemaBuilder.*;

class SerializationUtils {

    public static Unsafe unsafe = getUnsafeInstance();

    private static Unsafe getUnsafeInstance() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean writeNullableObject(UnsafeMemory unsafeMemory, Object object) {
        if (object == null) {
            unsafeMemory.writeByte((byte) 0);
            return true;
        } else {
            unsafeMemory.writeByte((byte) 1);
            return false;
        }
    }

    private static boolean readNullableObject(UnsafeMemory unsafeMemory) {
        return unsafeMemory.readByte() == 0;
    }

    private static void serializeObject(ClassInfo classInfo, UnsafeMemory unsafeMemory, Object object, long offset) {
        Object serializedObject = unsafe.getObject(object, offset);
        if (!writeNullableObject(unsafeMemory, serializedObject)) {
            for (FieldInfo f : classInfo.fieldInfos) {
                f.serialize(unsafeMemory, serializedObject);
            }
        }
    }

    private static void serializeVolatileObject(ClassInfo classInfo, UnsafeMemory unsafeMemory, Object object, long offset) {
        Object serializedObject = unsafe.getObjectVolatile(object, offset);
        if (!writeNullableObject(unsafeMemory, serializedObject)) {
            for (FieldInfo f : classInfo.fieldInfos) {
                f.serialize(unsafeMemory, serializedObject);
            }
        }
    }

    private static void deserializeObject(ClassInfo classInfo, UnsafeMemory unsafeMemory, Object object, long offset) {
        try {
            Object deserializedObject = null;
            if (!readNullableObject(unsafeMemory)) {
                deserializedObject = unsafe.allocateInstance(classInfo.clazz);
                for (FieldInfo f : classInfo.fieldInfos) {
                    f.deserialize(unsafeMemory, deserializedObject);
                }
            }
            unsafe.putObject(object, offset, deserializedObject);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to deserialize " + classInfo.clazz + ": " + e);
        }
    }

    private static void deserializeVolatileObject(ClassInfo classInfo, UnsafeMemory unsafeMemory, Object object, long offset) {
        try {
            Object deserializedObject = null;
            if (!readNullableObject(unsafeMemory)) {
                deserializedObject = unsafe.allocateInstance(classInfo.clazz);
                for (FieldInfo f : classInfo.fieldInfos) {
                    f.deserialize(unsafeMemory, deserializedObject);
                }
            }
            unsafe.putObjectVolatile(object, offset, deserializedObject);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to deserialize " + classInfo.clazz + ": " + e);
        }
    }


    static interface Serializer {

        void serialize(UnsafeMemory unsafeMemory, Object object, long offset);

    }

    static interface Deserializer {

        void deserialize(UnsafeMemory unsafeMemory, Object object, long offset);

    }

    static class IntSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeInt(unsafe.getInt(object, offset));
        }
    }

    static class IntVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeInt(unsafe.getIntVolatile(object, offset));
        }
    }

    static class IntDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putInt(object, offset, unsafeMemory.readInt());
        }
    }

    static class IntVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putIntVolatile(object, offset, unsafeMemory.readInt());
        }
    }

    static class LongSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLong(unsafe.getLong(object, offset));
        }
    }

    static class LongVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLong(unsafe.getLongVolatile(object, offset));
        }
    }

    static class LongDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putLong(object, offset, unsafeMemory.readLong());
        }
    }

    static class LongVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putLongVolatile(object, offset, unsafeMemory.readLong());
        }
    }

    static class ShortSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShort(unsafe.getShort(object, offset));
        }
    }

    static class ShortVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShort(unsafe.getShortVolatile(object, offset));
        }
    }

    static class ShortDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putShort(object, offset, unsafeMemory.readShort());
        }
    }

    static class ShortVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putShortVolatile(object, offset, unsafeMemory.readShort());
        }
    }

    static class DoubleSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDouble(unsafe.getDouble(object, offset));
        }
    }

    static class DoubleVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDouble(unsafe.getDoubleVolatile(object, offset));
        }
    }

    static class DoubleDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putDouble(object, offset, unsafeMemory.readDouble());
        }
    }

    static class DoubleVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putDoubleVolatile(object, offset, unsafeMemory.readDouble());
        }
    }

    static class FloatSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloat(unsafe.getFloat(object, offset));
        }
    }

    static class FloatVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloat(unsafe.getFloatVolatile(object, offset));
        }
    }

    static class FloatDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putFloat(object, offset, unsafeMemory.readFloat());
        }
    }

    static class FloatVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putFloatVolatile(object, offset, unsafeMemory.readFloat());
        }
    }

    static class ByteSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByte(unsafe.getByte(object, offset));
        }
    }

    static class ByteVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByte(unsafe.getByteVolatile(object, offset));
        }
    }

    static class ByteDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putByte(object, offset, unsafeMemory.readByte());
        }
    }

    static class ByteVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putByteVolatile(object, offset, unsafeMemory.readByte());
        }
    }

    static class BooleanSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBoolean(unsafe.getBoolean(object, offset));
        }
    }

    static class BooleanVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBoolean(unsafe.getBooleanVolatile(object, offset));
        }
    }

    static class BooleanDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putBoolean(object, offset, unsafeMemory.readBoolean());
        }
    }

    static class BooleanVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putBooleanVolatile(object, offset, unsafeMemory.readBoolean());
        }
    }

    static class CharSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeChar(unsafe.getChar(object, offset));
        }
    }

    static class CharVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeChar(unsafe.getCharVolatile(object, offset));
        }
    }

    static class CharDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putChar(object, offset, unsafeMemory.readChar());
        }
    }

    static class CharVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putCharVolatile(object, offset, unsafeMemory.readChar());
        }
    }

    static class DateSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeObject(dateClassInfo, unsafeMemory, object, offset);
        }
    }

    static class StringSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeObject(stringClassInfo, unsafeMemory, object, offset);
        }
    }

    static class StringVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeVolatileObject(stringClassInfo, unsafeMemory, object, offset);
        }
    }

    static class StringDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeObject(stringClassInfo, unsafeMemory, object, offset);
        }
    }

    static class DateDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeObject(dateClassInfo, unsafeMemory, object, offset);
        }
    }

    static class StringVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeVolatileObject(stringClassInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBufferSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeObject(stringBufferInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBufferVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeVolatileObject(stringBufferInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBufferDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeObject(stringBufferInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBufferVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeVolatileObject(stringBufferInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBuilderSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeObject(stringBuilderInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBuilderVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeVolatileObject(stringBuilderInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBuilderDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeObject(stringBuilderInfo, unsafeMemory, object, offset);
        }
    }

    static class StringBuilderVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeVolatileObject(stringBuilderInfo, unsafeMemory, object, offset);
        }
    }

    static class IntegerSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeInteger((Integer) unsafe.getObject(object, offset));
        }
    }

    static class IntegerVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeInteger((Integer) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class IntegerDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readInteger());
        }
    }

    static class IntegerVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readInteger());
        }
    }

    static class LongWrapperSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLongWrapper((Long) unsafe.getObject(object, offset));
        }
    }

    static class LongWrapperVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLongWrapper((Long) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class LongWrapperDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readLongWrapper());
        }
    }

    static class LongWrapperVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readLongWrapper());
        }
    }

    static class DoubleWrapperSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDoubleWrapper((Double) unsafe.getObject(object, offset));
        }
    }

    static class DoubleWrapperVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDoubleWrapper((Double) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class DoubleWrapperDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readDoubleWrapper());
        }
    }

    static class DoubleWrapperVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readDoubleWrapper());
        }
    }

    static class ShortWrapperSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShortWrapper((Short) unsafe.getObject(object, offset));
        }
    }

    static class ShortWrapperVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShortWrapper((Short) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class ShortWrapperDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readShortWrapper());
        }
    }

    static class ShortWrapperVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readShortWrapper());
        }
    }

    static class FloatWrapperSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloatWrapper((Float) unsafe.getObject(object, offset));
        }
    }

    static class FloatWrapperVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloatWrapper((Float) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class FloatWrapperDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readFloatWrapper());
        }
    }

    static class FloatWrapperVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readFloatWrapper());
        }
    }

    static class CharacterSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeCharacter((Character) unsafe.getObject(object, offset));
        }
    }

    static class CharacterVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeCharacter((Character) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class CharacterDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readCharacter());
        }
    }

    static class CharacterVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readCharacter());
        }
    }

    static class ByteWrapperSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByteWrapper((Byte) unsafe.getObject(object, offset));
        }
    }

    static class ByteWrapperVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByteWrapper((Byte) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class ByteWrapperDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readByteWrapper());
        }
    }

    static class ByteWrapperVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readByteWrapper());
        }
    }

    static class BooleanWrapperSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBooleanWrapper((Boolean) unsafe.getObject(object, offset));
        }
    }

    static class BooleanWrapperVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBooleanWrapper((Boolean) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class BooleanWrapperDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readBooleanWrapper());
        }
    }

    static class BooleanWrapperVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readBooleanWrapper());
        }
    }

    static class CharArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeCharArray((char[]) unsafe.getObject(object, offset));
        }
    }

    static class CharArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeCharArray((char[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class CharArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readCharArray());
        }
    }

    static class CharArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readCharArray());
        }
    }

    static class IntArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeIntArray((int[]) unsafe.getObject(object, offset));
        }
    }

    static class Int2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            int[][] ints = (int[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, ints)) {
                unsafeMemory.writeInt(ints.length);
                for (int[] anInt : ints) {
                    unsafeMemory.writeIntArray(anInt);
                }
            }
        }
    }

    static class Int2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            int[][] ints = (int[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, ints)) {
                unsafeMemory.writeInt(ints.length);
                for (int[] anInt : ints) {
                    unsafeMemory.writeIntArray(anInt);
                }
            }
        }
    }

    static class Long2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            long[][] longs = (long[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, longs)) {
                unsafeMemory.writeInt(longs.length);
                for (long[] aLong : longs) {
                    unsafeMemory.writeLongArray(aLong);
                }
            }
        }
    }

    static class Long2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            long[][] longs = (long[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, longs)) {
                unsafeMemory.writeInt(longs.length);
                for (long[] aLong : longs) {
                    unsafeMemory.writeLongArray(aLong);
                }
            }
        }
    }

    static class Double2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            double[][] doubles = (double[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, doubles)) {
                unsafeMemory.writeInt(doubles.length);
                for (double[] aDouble : doubles) {
                    unsafeMemory.writeDoubleArray(aDouble);
                }
            }
        }
    }

    static class Double2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            double[][] doubles = (double[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, doubles)) {
                unsafeMemory.writeInt(doubles.length);
                for (double[] aDouble : doubles) {
                    unsafeMemory.writeDoubleArray(aDouble);
                }
            }
        }
    }

    static class Float2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            float[][] floats = (float[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, floats)) {
                unsafeMemory.writeInt(floats.length);
                for (float[] aFloat : floats) {
                    unsafeMemory.writeFloatArray(aFloat);
                }
            }
        }
    }

    static class Float2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            float[][] floats = (float[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, floats)) {
                unsafeMemory.writeInt(floats.length);
                for (float[] aFloat : floats) {
                    unsafeMemory.writeFloatArray(aFloat);
                }
            }
        }
    }

    static class Short2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            short[][] shorts = (short[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, shorts)) {
                unsafeMemory.writeInt(shorts.length);
                for (short[] aShort : shorts) {
                    unsafeMemory.writeShortArray(aShort);
                }
            }
        }
    }

    static class Short2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            short[][] shorts = (short[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, shorts)) {
                unsafeMemory.writeInt(shorts.length);
                for (short[] aShort : shorts) {
                    unsafeMemory.writeShortArray(aShort);
                }
            }
        }
    }

    static class Char2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            char[][] chars = (char[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, chars)) {
                unsafeMemory.writeInt(chars.length);
                for (char[] aChar : chars) {
                    unsafeMemory.writeCharArray(aChar);
                }
            }
        }
    }

    static class Char2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            char[][] chars = (char[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, chars)) {
                unsafeMemory.writeInt(chars.length);
                for (char[] aChar : chars) {
                    unsafeMemory.writeCharArray(aChar);
                }
            }
        }
    }

    static class String2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            String[][] strings_2d = (String[][]) unsafe.getObject(object, offset);
            if (writeNullableObject(unsafeMemory, strings_2d)) {
                return;
            }
            unsafeMemory.writeInt(strings_2d.length);
            for (String[] stringArray : strings_2d) {
                if (writeNullableObject(unsafeMemory, stringArray)) {
                    continue;
                }
                unsafeMemory.writeInt(stringArray.length);
                for (String value : stringArray) {
                    if (!writeNullableObject(unsafeMemory, value)) {
                        unsafeMemory.writeString(value);
                    }
                }

            }

        }
    }

    static class String2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            String[][] strings_2d = (String[][]) unsafe.getObjectVolatile(object, offset);
            if (writeNullableObject(unsafeMemory, strings_2d)) {
                return;
            }
            unsafeMemory.writeInt(strings_2d.length);
            for (String[] stringArray : strings_2d) {
                if (writeNullableObject(unsafeMemory, stringArray)) {
                    continue;
                }
                unsafeMemory.writeInt(stringArray.length);
                for (String value : stringArray) {
                    if (!writeNullableObject(unsafeMemory, value)) {
                        unsafeMemory.writeString(value);
                    }
                }

            }

        }
    }

    static class String2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (readNullableObject(unsafeMemory)) {
                return;
            }
            String[][] strings_2d = new String[unsafeMemory.readInt()][];
            for (int i = 0; i < strings_2d.length; i++) {
                if (readNullableObject(unsafeMemory)) {
                    continue;
                }
                strings_2d[i] = new String[unsafeMemory.readInt()];
                for (int j = 0; j < strings_2d[i].length; j++) {
                    if (!readNullableObject(unsafeMemory)) {
                        strings_2d[i][j] = unsafeMemory.readString();
                    }
                }

            }
            unsafe.putObject(object, offset, strings_2d);

        }
    }

    static class String2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (readNullableObject(unsafeMemory)) {
                return;
            }
            String[][] strings_2d = new String[unsafeMemory.readInt()][];
            for (int i = 0; i < strings_2d.length; i++) {
                if (readNullableObject(unsafeMemory)) {
                    continue;
                }
                strings_2d[i] = new String[unsafeMemory.readInt()];
                for (int j = 0; j < strings_2d[i].length; j++) {
                    if (!readNullableObject(unsafeMemory)) {
                        strings_2d[i][j] = unsafeMemory.readString();
                    }
                }

            }
            unsafe.putObjectVolatile(object, offset, strings_2d);

        }
    }

    static class Byte2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            byte[][] bytes = (byte[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, bytes)) {
                unsafeMemory.writeInt(bytes.length);
                for (byte[] aByte : bytes) {
                    unsafeMemory.writeByteArray(aByte);
                }
            }
        }
    }

    static class Byte2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            byte[][] bytes = (byte[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, bytes)) {
                unsafeMemory.writeInt(bytes.length);
                for (byte[] aByte : bytes) {
                    unsafeMemory.writeByteArray(aByte);
                }
            }
        }
    }

    static class Boolean2DArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            boolean[][] booleans = (boolean[][]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, booleans)) {
                unsafeMemory.writeInt(booleans.length);
                for (boolean[] aBoolean : booleans) {
                    unsafeMemory.writeBooleanArray(aBoolean);
                }
            }
        }
    }

    static class Boolean2DArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            boolean[][] booleans = (boolean[][]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, booleans)) {
                unsafeMemory.writeInt(booleans.length);
                for (boolean[] aBoolean : booleans) {
                    unsafeMemory.writeBooleanArray(aBoolean);
                }
            }
        }
    }

    static class IntArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeIntArray((int[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class IntArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readIntArray());
        }
    }

    static class Int2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                int[][] ints_2d = new int[unsafeMemory.readInt()][];
                for (int i = 0; i < ints_2d.length; i++) {
                    ints_2d[i] = unsafeMemory.readIntArray();
                }
                unsafe.putObject(object, offset, ints_2d);
            }
        }
    }

    static class Int2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                int[][] ints_2d = new int[unsafeMemory.readInt()][];
                for (int i = 0; i < ints_2d.length; i++) {
                    ints_2d[i] = unsafeMemory.readIntArray();
                }
                unsafe.putObjectVolatile(object, offset, ints_2d);
            }
        }
    }

    static class Long2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                long[][] longs_2d = new long[unsafeMemory.readInt()][];
                for (int i = 0; i < longs_2d.length; i++) {
                    longs_2d[i] = unsafeMemory.readLongArray();
                }
                unsafe.putObject(object, offset, longs_2d);
            }
        }
    }

    static class Long2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                long[][] longs_2d = new long[unsafeMemory.readInt()][];
                for (int i = 0; i < longs_2d.length; i++) {
                    longs_2d[i] = unsafeMemory.readLongArray();
                }
                unsafe.putObjectVolatile(object, offset, longs_2d);
            }
        }
    }

    static class Double2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                double[][] doubles_2d = new double[unsafeMemory.readInt()][];
                for (int i = 0; i < doubles_2d.length; i++) {
                    doubles_2d[i] = unsafeMemory.readDoubleArray();
                }
                unsafe.putObject(object, offset, doubles_2d);
            }
        }
    }

    static class Double2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                double[][] doubles_2d = new double[unsafeMemory.readInt()][];
                for (int i = 0; i < doubles_2d.length; i++) {
                    doubles_2d[i] = unsafeMemory.readDoubleArray();
                }
                unsafe.putObjectVolatile(object, offset, doubles_2d);
            }
        }
    }

    static class Float2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                float[][] floats_2d = new float[unsafeMemory.readInt()][];
                for (int i = 0; i < floats_2d.length; i++) {
                    floats_2d[i] = unsafeMemory.readFloatArray();
                }
                unsafe.putObject(object, offset, floats_2d);
            }
        }
    }

    static class Float2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                float[][] floats_2d = new float[unsafeMemory.readInt()][];
                for (int i = 0; i < floats_2d.length; i++) {
                    floats_2d[i] = unsafeMemory.readFloatArray();
                }
                unsafe.putObjectVolatile(object, offset, floats_2d);
            }
        }
    }

    static class Short2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                short[][] shorts_2d = new short[unsafeMemory.readInt()][];
                for (int i = 0; i < shorts_2d.length; i++) {
                    shorts_2d[i] = unsafeMemory.readShortArray();
                }
                unsafe.putObject(object, offset, shorts_2d);
            }
        }
    }

    static class Short2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                short[][] shorts_2d = new short[unsafeMemory.readInt()][];
                for (int i = 0; i < shorts_2d.length; i++) {
                    shorts_2d[i] = unsafeMemory.readShortArray();
                }
                unsafe.putObjectVolatile(object, offset, shorts_2d);
            }
        }
    }

    static class Char2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                char[][] chars_2d = new char[unsafeMemory.readInt()][];
                for (int i = 0; i < chars_2d.length; i++) {
                    chars_2d[i] = unsafeMemory.readCharArray();
                }
                unsafe.putObject(object, offset, chars_2d);
            }
        }
    }

    static class Char2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                char[][] chars_2d = new char[unsafeMemory.readInt()][];
                for (int i = 0; i < chars_2d.length; i++) {
                    chars_2d[i] = unsafeMemory.readCharArray();
                }
                unsafe.putObjectVolatile(object, offset, chars_2d);
            }
        }
    }


    static class Byte2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                byte[][] bytes_2d = new byte[unsafeMemory.readInt()][];
                for (int i = 0; i < bytes_2d.length; i++) {
                    bytes_2d[i] = unsafeMemory.readByteArray();
                }
                unsafe.putObject(object, offset, bytes_2d);
            }
        }
    }

    static class Byte2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                byte[][] bytes_2d = new byte[unsafeMemory.readInt()][];
                for (int i = 0; i < bytes_2d.length; i++) {
                    bytes_2d[i] = unsafeMemory.readByteArray();
                }
                unsafe.putObjectVolatile(object, offset, bytes_2d);
            }
        }
    }

    static class Boolean2DArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                boolean[][] booleans_2d = new boolean[unsafeMemory.readInt()][];
                for (int i = 0; i < booleans_2d.length; i++) {
                    booleans_2d[i] = unsafeMemory.readBooleanArray();
                }
                unsafe.putObject(object, offset, booleans_2d);
            }
        }
    }

    static class Boolean2DArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            if (!readNullableObject(unsafeMemory)) {
                boolean[][] booleans_2d = new boolean[unsafeMemory.readInt()][];
                for (int i = 0; i < booleans_2d.length; i++) {
                    booleans_2d[i] = unsafeMemory.readBooleanArray();
                }
                unsafe.putObjectVolatile(object, offset, booleans_2d);
            }
        }
    }

    static class IntArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readIntArray());
        }
    }

    static class ShortArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShortArray((short[]) unsafe.getObject(object, offset));
        }
    }

    static class ShortArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShortArray((short[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class ShortArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readShortArray());
        }
    }

    static class ShortArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readShortArray());
        }
    }

    static class LongArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLongArray((long[]) unsafe.getObject(object, offset));
        }
    }

    static class LongArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLongArray((long[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class LongArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readLongArray());
        }
    }

    static class LongArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readLongArray());
        }
    }

    static class DoubleArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDoubleArray((double[]) unsafe.getObject(object, offset));
        }
    }

    static class DoubleArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDoubleArray((double[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class DoubleArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readDoubleArray());
        }
    }

    static class DoubleArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readDoubleArray());
        }
    }

    static class FloatArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloatArray((float[]) unsafe.getObject(object, offset));
        }
    }

    static class FloatArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloatArray((float[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class FloatArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readFloatArray());
        }
    }

    static class FloatArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readFloatArray());
        }
    }

    static class BooleanArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBooleanArray((boolean[]) unsafe.getObject(object, offset));
        }
    }

    static class BooleanArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBooleanArray((boolean[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class BooleanArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readBooleanArray());
        }
    }

    static class BooleanArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readBooleanArray());
        }
    }

    static class ByteArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByteArray((byte[]) unsafe.getObject(object, offset));
        }
    }

    static class ByteArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByteArray((byte[]) unsafe.getObjectVolatile(object, offset));
        }
    }

    static class ByteArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readByteArray());
        }
    }

    static class ByteArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObjectVolatile(object, offset, unsafeMemory.readByteArray());
        }
    }

    static class StringArraySerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            String[] stringArray = (String[]) unsafe.getObject(object, offset);
            if (!writeNullableObject(unsafeMemory, stringArray)) {
                unsafeMemory.writeInt(stringArray.length);
                for (String value : stringArray) {
                    if (!writeNullableObject(unsafeMemory, value)) {
                        unsafeMemory.writeString(value);
                    }
                }
            }
        }
    }

    static class StringArrayVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            String[] stringArray = (String[]) unsafe.getObjectVolatile(object, offset);
            if (!writeNullableObject(unsafeMemory, stringArray)) {
                unsafeMemory.writeInt(stringArray.length);
                for (String value : stringArray) {
                    if (!writeNullableObject(unsafeMemory, value)) {
                        unsafeMemory.writeString(value);
                    }
                }
            }
        }
    }

    static class StringArrayDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            String[] values = null;
            if (!readNullableObject(unsafeMemory)) {
                values = new String[unsafeMemory.readInt()];
                for (int i = 0; i < values.length; i++) {
                    if (!readNullableObject(unsafeMemory)) {
                        values[i] = unsafeMemory.readString();
                    }
                }
            }
            unsafe.putObject(object, offset, values);
        }
    }

    static class StringArrayVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            String[] values = null;
            if (!readNullableObject(unsafeMemory)) {
                values = new String[unsafeMemory.readInt()];
                for (int i = 0; i < values.length; i++) {
                    if (!readNullableObject(unsafeMemory)) {
                        values[i] = unsafeMemory.readString();
                    }
                }
            }
            unsafe.putObjectVolatile(object, offset, values);
        }
    }

    static class EnumSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            Object ordinal = unsafe.getInt(object, offset);
            unsafeMemory.writeInt((int) ordinal);
        }
    }

    static class EnumVolatileSerializer extends EnumSerializer {
    }

    static class BigIntegerSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeObject(bigIntegerClassInfo, unsafeMemory, object, offset);
        }
    }

    static class BigIntegerVolatileSerializer implements Serializer {
        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeVolatileObject(bigIntegerClassInfo, unsafeMemory, object, offset);
        }
    }

    static class BigIntegerDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeObject(bigIntegerClassInfo, unsafeMemory, object, offset);
        }
    }

    static class BigIntegerVolatileDeserializer implements Deserializer {
        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeVolatileObject(bigIntegerClassInfo, unsafeMemory, object, offset);
        }
    }

    static class BatchSerializer implements Serializer {
        private final int size;

        BatchSerializer(int size) {
            this.size = size;
        }

        @Override
        public void serialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBatch(object, offset, size);
        }
    }

    static class BatchDeserializer implements Deserializer {
        private final int size;

        BatchDeserializer(int size) {
            this.size = size;
        }

        @Override
        public void deserialize(UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.readBatch(object, offset, size);
        }
    }


}
