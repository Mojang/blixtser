package com.mojang.blixtser.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;

@State(Scope.Thread)
public class MicroBenchmark_AverageTime {

    private static SampleValue[] input = null;
    private static BlixtserSerializer blixtserSerializer = new BlixtserSerializer();
    private static FastSerializer fastSerializer = new FastSerializer();
    private static KryoSerializer kryoSerializer = new KryoSerializer();
    private static JavaBuiltInSerializer javaBuiltInSerializer = new JavaBuiltInSerializer();

    @Setup
    public void init() {
        ArrayList<SampleValue> inputList = new ArrayList<>();
        for (int i = 0; i < 550000; i++) {
            inputList.add(SampleValue.createRandom());
        }
        input = inputList.toArray(new SampleValue[inputList.size()]);
    }

    @Benchmark
    public void kryo() {
        for (SampleValue anInput : input) {
            byte[] serialized = kryoSerializer.serialize(anInput);
            kryoSerializer.deserialize(serialized);
        }
    }

    @Benchmark
    public void fastSerializer() {
        for (SampleValue anInput : input) {
            byte[] serialized = fastSerializer.serialize(anInput);
            fastSerializer.deserialize(serialized);
        }
    }

    @Benchmark
    public void blixtser() {
        for (SampleValue anInput : input) {
            byte[] serialized = blixtserSerializer.serialize(anInput);
            blixtserSerializer.deserialize(serialized);
        }
    }

    @Benchmark
    public void java_built_in_serializer() {
        for (SampleValue anInput : input) {
            byte[] serialized = javaBuiltInSerializer.serialize(anInput);
            javaBuiltInSerializer.deserialize(serialized);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(".*" + MicroBenchmark_AverageTime.class.getSimpleName() + ".*")
                .warmupIterations(1)
                .measurementIterations(10)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .mode(Mode.AverageTime)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
