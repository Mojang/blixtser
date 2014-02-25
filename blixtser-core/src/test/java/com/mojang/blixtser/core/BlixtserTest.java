package com.mojang.blixtser.core;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import static com.mojang.blixtser.core.TestClasses.*;

public class BlixtserTest {

    private Blixtser blixtser = new Blixtser();

    public BlixtserTest() {
        for (Class<?> c : TestClasses.class.getClasses()) {
            blixtser.register(c);
        }
    }

    @Test
    public void test_primitive_int() {
        int a = 16;
        IntPrimitiveTestClass testClass = new IntPrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_long() {
        long a = 162434234234211l;
        LongPrimitiveTestClass testClass = new LongPrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_double() {
        double a = 162434234.2323d;
        DoublePrimitiveTestClass testClass = new DoublePrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_float() {
        float a = 162434234.2323f;
        FloatPrimitiveTestClass testClass = new FloatPrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_char() {
        char a = '形';
        CharPrimitiveTestClass testClass = new CharPrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_short() {
        short a = 12;
        ShortPrimitiveTestClass testClass = new ShortPrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_byte() {
        byte a = 0x12;
        BytePrimitiveTestClass testClass = new BytePrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_boolean() {
        boolean a = true;
        BooleanPrimitiveTestClass testClass = new BooleanPrimitiveTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_enum() {
        EnumTestClass.SomeEnum a = EnumTestClass.SomeEnum.B;
        EnumTestClass testClass = new EnumTestClass(a, a);

        testSerializationFor(testClass);
    }

    @Test
    public void test_string() {
        String a = "Ämir & Daniel";
        String b = "Ämir & Daniel";
        StringTestClass testClass = new StringTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_int() {
        Integer a = 1615;
        Integer b = 1716;
        IntNonPrimitiveTestClass testClass = new IntNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_long() {
        Long a = 1615123l;
        Long b = 1615123l;
        LongNonPrimitiveTestClass testClass = new LongNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_double() {
        Double a = 1615123.23d;
        Double b = 1615123.23d;
        DoubleNonPrimitiveTestClass testClass = new DoubleNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_float() {
        Float a = 1615123.23f;
        Float b = 1615123.23f;
        FloatNonPrimitiveTestClass testClass = new FloatNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_char() {
        Character a = 'Ä';
        Character b = 'Ö';
        CharNonPrimitiveTestClass testClass = new CharNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_short() {
        Short a = 12;
        Short b = 12;
        ShortNonPrimitiveTestClass testClass = new ShortNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_byte() {
        Byte a = 0x12;
        Byte b = 0x13;
        ByteNonPrimitiveTestClass testClass = new ByteNonPrimitiveTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_non_primitive_boolean() {
        BooleanNonPrimitiveTestClass testClass = new BooleanNonPrimitiveTestClass(true, true);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_string_builder() {
        StringBuilder a = new StringBuilder();
        a.append("Ämir & Daniel");

        StringBuilder b = new StringBuilder();
        b.append("Ämir & Daniel");

        StringBuilderTestClass testClass = new StringBuilderTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);

    }

    @Test
    public void test_string_buffer() {
        StringBuffer a = new StringBuffer();
        a.append("Ämir & Daniel");

        StringBuffer b = new StringBuffer();
        b.append("Ämir & Daniel");

        StringBufferTestClass testClass = new StringBufferTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);

    }

    @Test
    public void test_primitive_int_array() {
        int[] a = new int[] {1, 2, 3, 4, 5, 6};
        int[] b = new int[] {1, 2, 3, 4, 5, 6};
        IntPrimitiveArrayTestClass testClass = new IntPrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_int_2d_array() {
        int[][] a = new int[][] { new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6} };
        int[][] b = new int[][] { new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6}, null };
        IntPrimitive2DArrayTestClass testClass = new IntPrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_long_array() {
        long[] a = new long[] {1l, 2l, 3l, 4l, 5l, 6l};
        long[] b = new long[] {1l, 2l, 3l, 4l, 5l, 6l};
        LongPrimitiveArrayTestClass testClass = new LongPrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_long_2d_array() {
        long[][] a = new long[][] {new long[]{1l}, null, new long[]{2l, 3l, 4l, 5l, 6l}};
        long[][] b = new long[][] {new long[]{1l}, new long[]{2l, 3l, 4l, 5l, 6l}, null};
        LongPrimitive2DArrayTestClass testClass = new LongPrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_double_array() {
        double[] a = new double[] {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
        double[] b = new double[] {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
        DoublePrimitiveArrayTestClass testClass = new DoublePrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_double_2d_array() {
        double[][] a = new double[][] { new double[]{1.1, 2.2, 3.3}, new double[]{4.4, 5.5, 6.6}, null};
        double[][] b = new double[][] { new double[]{1.1, 2.2, 3.3}, null, new double[]{4.4, 5.5, 6.6}};
        DoublePrimitive2DArrayTestClass testClass = new DoublePrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_float_array() {
        float[] a = new float[] {1.1f, 2.2f, 3.3f, 4.4f, 5.5f, 6.6f};
        float[] b = new float[] {1.1f, 2.2f, 3.3f, 4.4f, 5.5f, 6.6f};
        FloatPrimitiveArrayTestClass testClass = new FloatPrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_float_2d_array() {
        float[][] a = new float[][] { new float[]{1.1f, 2.2f, 3.3f}, null, new float[]{4.4f, 5.5f, 6.6f}};
        float[][] b = new float[][] { new float[]{1.1f, 2.2f, 3.3f}, null, new float[]{4.4f, 5.5f, 6.6f}};
        FloatPrimitive2DArrayTestClass testClass = new FloatPrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_char_array() {
        char[] a = new char[] {'A', 'm', 'i', 'r'};
        char[] b = new char[] {'A', 'm', 'i', 'r'};
        CharPrimitiveArrayTestClass testClass = new CharPrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_char_2d_array() {
        char[][] a = new char[][] { new char[]{'A', 'm'}, null, new char[]{'i', 'r'}};
        char[][] b = new char[][] { new char[]{'A', 'm'}, new char[]{'i', 'r'}, null};
        CharPrimitive2DArrayTestClass testClass = new CharPrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_short_array() {
        short[] a = new short[] {12, 13, 14, 15};
        short[] b = new short[] {12, 13, 14, 15};
        ShortPrimitiveArrayTestClass testClass = new ShortPrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_short_2d_array() {
        short[][] a = new short[][] { new short[]{12, 13}, null, new short[]{14, 15}};
        short[][] b = new short[][] { new short[]{12, 13}, new short[]{14, 15}, null};
        ShortPrimitive2DArrayTestClass testClass = new ShortPrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_byte_array() {
        byte[] a = new byte[] {0x12, 0x13, 0x14, 0x15};
        byte[] b = new byte[] {0x12, 0x13, 0x14, 0x15};
        BytePrimitiveArrayTestClass testClass = new BytePrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_byte_2d_array() {
        byte[][] a = new byte[][] { new byte[]{0x12, 0x13}, null, new byte[]{0x14, 0x15}};
        byte[][] b = new byte[][] { new byte[]{0x12, 0x13}, new byte[]{0x14, 0x15}, null};
        BytePrimitive2DArrayTestClass testClass = new BytePrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_boolean_array() {
        boolean[] a = new boolean[] {false, true, false, true};
        boolean[] b = new boolean[] {false, true, false, true};
        BooleanPrimitiveArrayTestClass testClass = new BooleanPrimitiveArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_primitive_boolean_2d_array() {
        boolean[][] a = new boolean[][] { new boolean[]{false, true}, new boolean[]{false, true}};
        boolean[][] b = new boolean[][] { new boolean[]{false, true}, new boolean[]{false, true}};
        BooleanPrimitive2DArrayTestClass testClass = new BooleanPrimitive2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);
        testSerializationFor(testClass);
    }

    @Test
    public void test_string_array() {
        String[] a = new String[] {"Ämir", "Daniel"};
        String[] b = new String[] {"Ämir", "Daniel"};
        StringArrayTestClass testClass = new StringArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_string_2d_array() {
        String[][] a = new String[][] { new String[]{"Ämir", "Moulavi", null}, null, new String[]{"Daniel", "Frisk"}};
        String[][] b = new String[][] { new String[]{"Ämir", "Moulavi", null}, null, new String[]{"Daniel", "Frisk"}};
        String2DArrayTestClass testClass = new String2DArrayTestClass(a, b);

        testSerializationFor(testClass);

        testClass.setA(null);
        testClass.setB(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_date() {
        Date a = Calendar.getInstance().getTime();
        DateTestClass testClass = new DateTestClass();
        testClass.setA(a);

        testSerializationFor(testClass);

        testClass.setA(null);

        testSerializationFor(testClass);
    }

    @Test
    public void test_big_int() {
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
