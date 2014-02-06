package com.mojang.blixtser.benchmark;

import de.ruedigermoeller.serialization.FSTConfiguration;
import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.FSTObjectOutput;

import java.io.IOException;

public class FastSerializer implements Serializer {

    final FSTObjectOutput out;
    final FSTObjectInput in;

    {
        FSTConfiguration fst = FSTConfiguration.createDefaultConfiguration();
        fst.setShareReferences(false);
        fst.setIgnoreSerialInterfaces(true);
        fst.setPreferSpeed(true);
        fst.registerClass(SampleValue.class);
        out = fst.getObjectOutput(null);
        in = fst.getObjectInput(new byte[0], 0, 0);
    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            synchronized (out) {
                out.resetForReUse(null);
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
                in.resetForReuseUseArray(arr, 0, arr.length);
                return in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
