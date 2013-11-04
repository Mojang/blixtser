package serialization;

import com.mojang.serialization.UnsafeSerializer;

public class MoriatimSerializer implements Serializer{

    UnsafeSerializer unsafeSerializer = new UnsafeSerializer();

    public MoriatimSerializer() {
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
