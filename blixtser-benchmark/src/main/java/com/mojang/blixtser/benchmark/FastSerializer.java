package com.mojang.blixtser.benchmark;


import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.IOException;

public class FastSerializer implements Serializer {

    final FSTObjectOutput out;
    final FSTObjectInput in;

    byte[] buffer = new byte[4096];

    {
        FSTConfiguration fst = FSTConfiguration.createDefaultConfiguration();
        fst.setShareReferences(false);
        fst.setPreferSpeed(true);
        fst.registerClass(SampleValue.class);
        out = fst.getObjectOutput(buffer);
        in = fst.getObjectInput(buffer);
    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            synchronized (out) {
                out.resetForReUse(buffer);
                out.writeObject(obj);
                return out.getBuffer();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object deserialize(byte[] arr) {
        try {
            synchronized (in) {
                in.resetForReuseUseArray(arr);
                return in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
