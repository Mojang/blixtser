package serialization;

import com.esotericsoftware.kryo.Kryo;

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
        kryo.writeObject(byteBuffer, obj);
        return byteBuffer.array();
    }

    @Override
    public Object deserialize(byte[] arr) {
        return kryo.readObject(ByteBuffer.wrap(arr), SampleValue.class);
    }
}
