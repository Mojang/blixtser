package com.mojang.serialization;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.*;

public class SerializationUtils {

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

    private static Map<Integer, Serializable> serializableCache = new HashMap<>();


    interface Serializable {

        void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset);

        void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value);

        void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset);

        Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory);

    }

    /**
     *
     */
    static class IntSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeInt(unsafe.getInt(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeInt((int) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putInt(object, offset, unsafeMemory.readInt());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readInt();
        }
    }

    /**
     *
     */
    static class LongSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLong(unsafe.getLong(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeLong((long) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putLong(object, offset, unsafeMemory.readLong());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readLong();
        }
    }

    /**
     *
     */
    static class ShortSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShort(unsafe.getShort(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeShort((short) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putShort(object, offset, unsafeMemory.readShort());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readShort();
        }
    }

    /**
     *
     */
    static class DoubleSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDouble(unsafe.getDouble(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeDouble((double) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putDouble(object, offset, unsafeMemory.readDouble());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readDouble();
        }
    }

    /**
     *
     */
    static class FloatSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloat(unsafe.getFloat(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeFloat((float) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putFloat(object, offset, unsafeMemory.readFloat());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readFloat();
        }

    }

    /**
     *
     */
    static class ByteSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByte(unsafe.getByte(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeByte((byte) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putByte(object, offset, unsafeMemory.readByte());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readByte();
        }
    }

    /**
     *
     */
    static class BooleanSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBoolean(unsafe.getBoolean(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeBoolean((boolean) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putBoolean(object, offset, unsafeMemory.readBoolean());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readBoolean();
        }
    }

    /**
     *
     */
    static class CharSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeChar(unsafe.getChar(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeChar((char) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putChar(object, offset, unsafeMemory.readChar());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readChar();
        }
    }

    /**
     *
     */
    static class StringSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeString((String) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeString((String) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readString());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readString();
        }
    }

    /**
     *
     */
    static class IntegerSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeInteger((Integer) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeInteger((Integer) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readInteger());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readInteger();
        }
    }

    /**
     *
     */
    static class LongWrapperSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLongWrapper((Long) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeLongWrapper((Long) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readLongWrapper());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readLongWrapper();
        }
    }

    /**
     *
     */
    static class DoubleWrapperSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDoubleWrapper((Double) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeDoubleWrapper((Double) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readDoubleWrapper());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readDoubleWrapper();
        }
    }

    /**
     *
     */
    static class ShortWrapperSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShortWrapper((Short) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeShortWrapper((Short) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readShortWrapper());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readShortWrapper();
        }
    }

    /**
     *
     */
    static class FloatWrapperSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloatWrapper((Float) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeFloatWrapper((Float) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readFloatWrapper());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readFloatWrapper();
        }
    }

    /**
     *
     */
    static class CharacterSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeCharacter((Character) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeCharacter((Character) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readCharacter());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readCharacter();
        }
    }

    /**
     *
     */
    static class ByteWrapperSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByteWrapper((Byte) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeByteWrapper((Byte) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readByteWrapper());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readByteWrapper();
        }
    }

    /**
     *
     */
    static class BooleanWrapperSerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBooleanWrapper((Boolean) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeBooleanWrapper((Boolean) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readBooleanWrapper());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readByteWrapper();
        }
    }

    /**
     *
     */
    static class CharArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeCharArray((char[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeCharArray((char[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readCharArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readCharArray();
        }
    }

    /**
     *
     */
    static class IntArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeIntArray((int[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeIntArray((int[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readIntArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readIntArray();
        }
    }

    /**
     *
     */
    static class ShortArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeShortArray((short[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeShortArray((short[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readShortArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readShortArray();
        }
    }

    /**
     *
     */
    static class LongArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeLongArray((long[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeLongArray((long[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readLongArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readLongArray();
        }
    }

    /**
     *
     */
    static class DoubleArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeDoubleArray((double[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeDoubleArray((double[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readDoubleArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readDoubleArray();
        }
    }

    /**
     *
     */
    static class FloatArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeFloatArray((float[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeFloatArray((float[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readFloatArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readFloatArray();
        }
    }

    /**
     *
     */
    static class BooleanArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeBooleanArray((boolean[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeBooleanArray((boolean[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readBooleanArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readBooleanArray();
        }
    }

    /**
     *
     */
    static class ByteArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeByteArray((byte[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeByteArray((byte[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readByteArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readByteArray();
        }
    }

    /**
     *
     */
    static class StringArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafeMemory.writeStringArray((String[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            unsafeMemory.writeStringArray((String[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            unsafe.putObject(object, offset, unsafeMemory.readStringArray());
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return unsafeMemory.readStringArray();
        }
    }

    /**
     *
     */
    static class ObjectArraySerializer implements Serializable {

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            serializeCollection(unsafeMemory, (Object[]) unsafe.getObject(object, offset));
        }

        @Override
        public void serialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object value) {
            serializeCollection(unsafeMemory, (Object[]) value);
        }

        @Override
        public void deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
            deserializeObjectArray(unsafeMemory, object, offset);
        }

        @Override
        public Object deserialize(UnsafeSerializer.UnsafeMemory unsafeMemory) {
            return deserializeCollection(unsafeMemory);
        }
    }

    private static void deserializeObjectArray(UnsafeSerializer.UnsafeMemory unsafeMemory, Object object, long offset) {
        byte flag = unsafeMemory.readByte();
        Object[] objs = null;
        if (flag == 1) {
            int size = unsafeMemory.readInt();
            objs = new Object[size];
            if (size != 0) {
                int hash = unsafeMemory.readInt();
                Serializable serializable = serializableCache.get(hash);
                if (serializable == null) {
                    throw new UnknownRegisteredHashCode();
                }
                for (int i = 0; i < size; i++) {
                    objs[i] = serializable.deserialize(unsafeMemory);
                }
            }
        }
        unsafe.putObject(object, offset, objs);
    }

    private static Object[] deserializeCollection(UnsafeSerializer.UnsafeMemory unsafeMemory) {
        byte flag = unsafeMemory.readByte();
        Object[] objs = null;
        if (flag == 1) {
            int size = unsafeMemory.readInt();
            objs = new Object[size];
            if (size != 0) {
                int hash = unsafeMemory.readInt();
                Serializable serializable = serializableCache.get(hash);
                if (serializable == null) {
                    throw new UnknownRegisteredHashCode();
                }
                for (int i = 0; i < size; i++) {
                    objs[i] = serializable.deserialize(unsafeMemory);
                }
            }
        }
        return objs;
    }


    private static void serializeCollection(UnsafeSerializer.UnsafeMemory unsafeMemory, Object[] object) {
        if (object != null) {
            unsafeMemory.writeByte((byte) 1);
            int size = object.length;
            unsafeMemory.writeInt(size);
            if (size != 0) {
                Object firstElement = object[0];
                Class<?> firstElementClass = firstElement.getClass();
                Serializable collectionTypedParameter = ClassSchemaBuilder.typeRegistry.get(firstElementClass);
                if (collectionTypedParameter != null) {
                    int hash = firstElement.hashCode();
                    serializableCache.put(hash, collectionTypedParameter);
                    unsafeMemory.writeInt(hash);
                    collectionTypedParameter.serialize(unsafeMemory, firstElement);
                    for (int i = 1; i < size; i++) {
                        collectionTypedParameter.serialize(unsafeMemory, object[i]);
                    }
                } else {
                    throw new UnknownRegisteredTypeException(firstElement.getClass().getName());
                }
            }
        } else {
            unsafeMemory.writeByte((byte) 0);
        }
    }
}
