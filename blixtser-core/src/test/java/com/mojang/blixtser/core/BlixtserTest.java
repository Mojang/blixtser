package com.mojang.blixtser.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static com.mojang.blixtser.core.TestClasses.*;

public class BlixtserTest {

    private Blixtser blixtser = new Blixtser();

    @Before
    public void setup() {
        blixtser.register(SerializableClass.class);
        blixtser.register(NullClass.class);

        blixtser.register(IntPrimitiveTestClass.class);
        blixtser.register(IntNonPrimitiveTestClass.class);
        blixtser.register(IntPrimitiveArrayTestClass.class);
        blixtser.register(BigIntegerTestClass.class);

        blixtser.register(LongPrimitiveTestClass.class);
        blixtser.register(LongNonPrimitiveTestClass.class);
        blixtser.register(LongPrimitiveArrayTestClass.class);

        blixtser.register(DoublePrimitiveTestClass.class);
        blixtser.register(DoubleNonPrimitiveTestClass.class);
        blixtser.register(DoublePrimitiveArrayTestClass.class);
    }

    @Test
    public void test_primitive_int() {
        int a = 16;
        IntPrimitiveTestClass testClass = new IntPrimitiveTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_long() {
        long a = 162434234234211l;
        LongPrimitiveTestClass testClass = new LongPrimitiveTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_double() {
        double a = 162434234.2323d;
        DoublePrimitiveTestClass testClass = new DoublePrimitiveTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_int() {
        Integer a = 1615;
        IntNonPrimitiveTestClass testClass = new IntNonPrimitiveTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);

        testClass.setA(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_long() {
        Long a = 1615123l;
        LongNonPrimitiveTestClass testClass = new LongNonPrimitiveTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);

        testClass.setA(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_double() {
        Double a = 1615123.23d;
        DoubleNonPrimitiveTestClass testClass = new DoubleNonPrimitiveTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);

        testClass.setA(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_int_array() {
        int[] a = new int[] {1, 2, 3, 4, 5, 6};
        IntPrimitiveArrayTestClass testClass = new IntPrimitiveArrayTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_long_array() {
        long[] a = new long[] {1l, 2l, 3l, 4l, 5l, 6l};
        LongPrimitiveArrayTestClass testClass = new LongPrimitiveArrayTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_double_array() {
        double[] a = new double[] {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
        DoublePrimitiveArrayTestClass testClass = new DoublePrimitiveArrayTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_big_int_array() {
        BigInteger a = new BigInteger("124412");
        BigIntegerTestClass testClass = new BigIntegerTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_serialization_deserialization() {
        SerializableClass serializableClass = SerializableClass.createAnObject();
        byte[] serialized = blixtser.serialize(serializableClass);
        Object deserialized = blixtser.deserialize(serialized);

        Assert.assertTrue(serializableClass.equals(deserialized));
    }

    @Test
    public void test_null_objects() {
        NullClass nullClass = new NullClass();
        byte[] serialized = blixtser.serialize(nullClass);
        Object deserialized = blixtser.deserialize(serialized);

        Assert.assertTrue(nullClass.equals(deserialized));
    }

    private void testSerializationFor(Object testClass) {
        byte[] serialized = blixtser.serialize(testClass);
        Object deserialized = blixtser.deserialize(serialized);

        Assert.assertTrue(testClass.equals(deserialized));
    }

}
