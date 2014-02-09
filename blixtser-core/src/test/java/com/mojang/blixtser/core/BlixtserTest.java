package com.mojang.blixtser.core;

import org.junit.Assert;
import org.junit.Test;

public class BlixtserTest {

    private Blixtser blixtser = new Blixtser();

    @Test
    public void test_serialization_deserialization() {
        blixtser.register(SerializableClass.class);

        SerializableClass serializableClass = SerializableClass.createAnObject();
        byte[] serialized = blixtser.serialize(serializableClass);
        Object deserialized = blixtser.deserialize(serialized);

        Assert.assertTrue(serializableClass.equals(deserialized));
    }

    @Test
    public void test_null_objects() {
        blixtser.register(NullClass.class);

        NullClass nullClass = new NullClass();
        byte[] serialized = blixtser.serialize(nullClass);
        Object deserialized = blixtser.deserialize(serialized);

        Assert.assertTrue(nullClass.equals(deserialized));
    }

}
