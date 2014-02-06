package com.mojang.blixtser.benchmark;

import com.mojang.blixtser.core.UnsafeSerializer;

public class BlixtserSerializer implements Serializer {

    UnsafeSerializer unsafeSerializer = new UnsafeSerializer();

    public BlixtserSerializer() {
        unsafeSerializer.register(SampleValue.class);
    }

    @Override
    public byte[] serialize(Object obj) {
        return unsafeSerializer.serialize(obj);
    }

    @Override
    public Object deserialize(byte[] arr) {
        return unsafeSerializer.deserialize(arr);
    }
}
