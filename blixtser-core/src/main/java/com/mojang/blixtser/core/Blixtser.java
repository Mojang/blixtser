package com.mojang.blixtser.core;

import java.io.*;
import java.util.HashSet;

import static com.mojang.blixtser.core.ClassSchemaBuilder.*;

@SuppressWarnings("all")
public class Blixtser {

    private final UnsafeMemory unsafeMemory = new UnsafeMemory();
    private final ClassSchemaBuilder classSchemaBuilder = new ClassSchemaBuilder();

    public void register(Class<?> c) {
        classSchemaBuilder.registerClass(c, new HashSet<String>());
    }

    public byte[] serialize(Object obj) {
        unsafeMemory.reset();

        Class<?> c = obj.getClass();
        int code = c.hashCode();
        unsafeMemory.writeInt(code);

        for (FieldInfo f : classInfoCache.get(code).fieldInfos) {
            f.serialize(unsafeMemory, obj);
        }

        return unsafeMemory.getBuffer();
    }

    public Object deserialize(byte[] data) {
        UnsafeMemory unsafeMemory = new UnsafeMemory(data);

        int code = unsafeMemory.readInt();
        ClassInfo classInfo = classInfoCache.get(code);
        try {
            //Object obj = unsafe.allocateInstance(classInfo.clazz);
            Object obj = classInfo.instance();

            for (FieldInfo f : classInfo.fieldInfos) {
                f.deserialize(unsafeMemory, obj);
            }

            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeObject(Object obj, OutputStream out) throws IOException {
        byte[] data = serialize(obj);
        int size = data.length;

        out.write((size >>> 24) & 0xFF);
        out.write((size >>> 16) & 0xFF);
        out.write((size >>> 8) & 0xFF);
        out.write(size & 0xFF);
        out.write(data);
    }

    public Object readObject(InputStream in) throws IOException {
        int b3 = in.read();
        int b2 = in.read();
        int b1 = in.read();
        int b0 = in.read();
        if ((b3 | b2 | b1 | b0) < 0) {
            throw new EOFException();
        }

        int size = (b3 << 24) + (b2 << 16) + (b1 << 8) + b0;
        byte[] data = new byte[size];

        for (int i = 0; i < size; i++) {
            int d = in.read();
            if (d < 0) {
                throw new EOFException("Couldn't read object from InputStream, EOF");
            }
            data[i] = (byte) d;
        }

        return deserialize(data);
    }

    /**
     *
     */

}
