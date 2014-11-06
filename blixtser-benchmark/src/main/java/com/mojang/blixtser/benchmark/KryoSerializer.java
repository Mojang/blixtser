package com.mojang.blixtser.benchmark;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.nio.ByteBuffer;

public class KryoSerializer implements Serializer {

    private Kryo kryo = new Kryo();
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public KryoSerializer() {
        kryo.register(SampleValue.class);
    }

    @Override
    public byte[] serialize(Object obj) {
        byteBuffer.clear();
        Output output = new Output(byteBuffer.array());
        kryo.writeObject(output, obj);
        return byteBuffer.array();
    }

    @Override
    public Object deserialize(byte[] arr) {
        Input input = new Input(arr);
        return kryo.readObject(input, SampleValue.class);
    }
}
