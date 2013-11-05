package serialization;

import java.util.ArrayList;

public class SerializerPerformanceTest {

    Serializer[] serializers = new Serializer[]{new MojangSerializer(), new FastSerializer()};
    static SampleValue[] input = null;

    public void setUp() {
        ArrayList<SampleValue> inputList = new ArrayList<>();
        for (int i = 0; i < 550000; i++) {
            inputList.add(SampleValue.createRandom());
        }
        input = inputList.toArray(new SampleValue[inputList.size()]);
    }

    public void bench() throws InterruptedException {
        setUp();

        for (int i = 0; i < 4; i++) {
            for (Serializer serializer : serializers) {
                run(serializer);
                System.gc();
                System.gc();
                Thread.sleep(1000);
            }
        }

    }

    private void run(Serializer serializer) {
        SampleValue[] output = new SampleValue[input.length];

        long start = System.currentTimeMillis();

        for (int i = 0; i < input.length; i++) {
            byte[] serialized = serializer.serialize(input[i]);
            output[i] = (SampleValue) serializer.deserialize(serialized);
        }

        System.out.println("Bench finished for serializer '" + serializer.getClass().getSimpleName() + "': " + " took: "
                + (System.currentTimeMillis() - start) + "ms!");

        for (int i = 0; i < input.length; i++) {
            if (!input[i].equals(output[i])) {
                System.out.println("Output was faulty...");
                return;
            }
        }
        System.out.println("Output was ok!");
    }

    public static void main(String[] args) throws InterruptedException {
        SerializerPerformanceTest test = new SerializerPerformanceTest();
        test.bench();
    }

}
