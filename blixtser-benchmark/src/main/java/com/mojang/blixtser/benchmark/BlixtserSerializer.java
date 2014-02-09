package com.mojang.blixtser.benchmark;

import com.mojang.blixtser.core.Blixtser;

public class BlixtserSerializer implements Serializer {

    Blixtser blixtser = new Blixtser();

    public BlixtserSerializer() {
        blixtser.register(SampleValue.class);
    }

    @Override
    public byte[] serialize(Object obj) {
        return blixtser.serialize(obj);
    }

    @Override
    public Object deserialize(byte[] arr) {
        return blixtser.deserialize(arr);
    }
}
