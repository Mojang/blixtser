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

}
