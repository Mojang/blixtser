package com.mojang.blixtser.benchmark;

import java.io.*;

/**
 *
 * @author dfrisk
 */
public class SimpleSerializer implements Serializer {

    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutput oo = new ObjectOutputStream(baos)) {
            oo.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object deserialize(byte[] arr) {
        try (InputStream is = new ByteArrayInputStream(arr); ObjectInput oi = new ObjectInputStream(is)) {
            return oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
