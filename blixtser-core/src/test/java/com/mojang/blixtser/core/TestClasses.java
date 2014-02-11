package com.mojang.blixtser.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

public class TestClasses {

    /**
     * Integers
     */

    @Data
    @EqualsAndHashCode
    public static class IntPrimitiveTestClass {
        private int a;
    }

    @Data
    @EqualsAndHashCode
    public static class IntNonPrimitiveTestClass {
        private Integer a;
    }

    @Data
    @EqualsAndHashCode
    public static class IntPrimitiveArrayTestClass {
        private int[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class IntPrimitive2DArrayTestClass {
        private int[][] a;
    }

    @Data
    @EqualsAndHashCode
    public static class BigIntegerTestClass {
        private BigInteger a;
    }

    /**
     *  Longs
     */

    @Data
    @EqualsAndHashCode
    public static class LongPrimitiveTestClass {
        private long a;
    }

    @Data
    @EqualsAndHashCode
    public static class LongNonPrimitiveTestClass {
        private Long a;
    }

    @Data
    @EqualsAndHashCode
    public static class LongPrimitiveArrayTestClass {
        private long[] a;
    }

    /**
     *  Doubles
     */

    @Data
    @EqualsAndHashCode
    public static class DoublePrimitiveTestClass {
        private double a;
    }

    @Data
    @EqualsAndHashCode
    public static class DoubleNonPrimitiveTestClass {
        private Double a;
    }

    @Data
    @EqualsAndHashCode
    public static class DoublePrimitiveArrayTestClass {
        private double[] a;
    }

    /**
     *  Floats
     */

    @Data
    @EqualsAndHashCode
    public static class FloatPrimitiveTestClass {
        private float a;
    }

    @Data
    @EqualsAndHashCode
    public static class FloatNonPrimitiveTestClass {
        private Float a;
    }

    @Data
    @EqualsAndHashCode
    public static class FloatPrimitiveArrayTestClass {
        private float[] a;
    }

    /**
     *  Char
     */

    @Data
    @EqualsAndHashCode
    public static class CharPrimitiveTestClass {
        private char a;
    }

    @Data
    @EqualsAndHashCode
    public static class CharNonPrimitiveTestClass {
        private Character a;
    }

    @Data
    @EqualsAndHashCode
    public static class CharPrimitiveArrayTestClass {
        private char[] a;
    }

    /**
     *  Short
     */

    @Data
    @EqualsAndHashCode
    public static class ShortPrimitiveTestClass {
        private short a;
    }

    @Data
    @EqualsAndHashCode
    public static class ShortNonPrimitiveTestClass {
        private Short a;
    }

    @Data
    @EqualsAndHashCode
    public static class ShortPrimitiveArrayTestClass {
        private short[] a;
    }

    /**
     *  Byte
     */

    @Data
    @EqualsAndHashCode
    public static class BytePrimitiveTestClass {
        private byte a;
    }

    @Data
    @EqualsAndHashCode
    public static class ByteNonPrimitiveTestClass {
        private Byte a;
    }

    @Data
    @EqualsAndHashCode
    public static class BytePrimitiveArrayTestClass {
        private byte[] a;
    }

    /**
     *  Boolean
     */

    @Data
    @EqualsAndHashCode
    public static class BooleanPrimitiveTestClass {
        private boolean a;
    }

    @Data
    @EqualsAndHashCode
    public static class BooleanNonPrimitiveTestClass {
        private Boolean a;
    }

    @Data
    @EqualsAndHashCode
    public static class BooleanPrimitiveArrayTestClass {
        private boolean[] a;
    }

    /**
     *  String
     */

    @Data
    @EqualsAndHashCode
    public static class StringTestClass {
        private String a;
    }

    @Data
    public static class StringBuilderTestClass {
        private StringBuilder a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StringBuilderTestClass)) return false;

            StringBuilderTestClass that = (StringBuilderTestClass) o;

            return !(a != null && that.a != null) || a.toString().equals(that.a.toString());

        }

        @Override
        public int hashCode() {
            return a != null ? a.hashCode() : 0;
        }
    }

    @Data
    public static class StringBufferTestClass {
        private StringBuffer a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StringBufferTestClass)) return false;

            StringBufferTestClass that = (StringBufferTestClass) o;

            return !(a != null && that.a != null) || a.toString().equals(that.a.toString());

        }

        @Override
        public int hashCode() {
            return a != null ? a.hashCode() : 0;
        }

    }

    @Data
    @EqualsAndHashCode
    public static class StringArrayTestClass {
        private String[] a;
    }

    /**
     *  Enums
     */

    @Data
    @EqualsAndHashCode
    public static class EnumTestClass {
        private SomeEnum a;

        public enum SomeEnum {
            A,
            B,
            C
        }
    }

}
