package serialization;

import com.mojang.serialization.UnsafeSerializer;

public class MojangSerializer implements Serializer {

    UnsafeSerializer unsafeSerializer = new UnsafeSerializer();

    public MojangSerializer() {
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
