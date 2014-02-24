package com.mojang.blixtser.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

public class TestClasses {

    /**
     * Integers
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class IntPrimitiveTestClass {
        int a;
        volatile int b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class IntNonPrimitiveTestClass {
        private Integer a;
        volatile Integer b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class IntPrimitiveArrayTestClass {
        int[] a;
        volatile int[] b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class IntPrimitive2DArrayTestClass {
        int[][] a;
        volatile int[][] b;
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
    @AllArgsConstructor
    public static class LongPrimitiveTestClass {
        long a;
        volatile long b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class LongNonPrimitiveTestClass {
        Long a;
        volatile Long b;
    }

    @Data
    @EqualsAndHashCode
    public static class LongPrimitiveArrayTestClass {
        private long[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class LongPrimitive2DArrayTestClass {
        private long[][] a;
    }

    /**
     *  Doubles
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class DoublePrimitiveTestClass {
        double a;
        volatile double b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class DoubleNonPrimitiveTestClass {
        Double a;
        volatile Double b;
    }

    @Data
    @EqualsAndHashCode
    public static class DoublePrimitiveArrayTestClass {
        private double[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class DoublePrimitive2DArrayTestClass {
        private double[][] a;
    }

    /**
     *  Floats
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class FloatPrimitiveTestClass {
        float a;
        volatile float b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class FloatNonPrimitiveTestClass {
        Float a;
        volatile Float b;
    }

    @Data
    @EqualsAndHashCode
    public static class FloatPrimitiveArrayTestClass {
        private float[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class FloatPrimitive2DArrayTestClass {
        private float[][] a;
    }

    /**
     *  Char
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class CharPrimitiveTestClass {
        char a;
        volatile char b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class CharNonPrimitiveTestClass {
        Character a;
        volatile Character b;
    }

    @Data
    @EqualsAndHashCode
    public static class CharPrimitiveArrayTestClass {
        private char[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class CharPrimitive2DArrayTestClass {
        private char[][] a;
    }

    /**
     *  Short
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class ShortPrimitiveTestClass {
        short a;
        volatile short b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class ShortNonPrimitiveTestClass {
        Short a;
        volatile Short b;
    }

    @Data
    @EqualsAndHashCode
    public static class ShortPrimitiveArrayTestClass {
        private short[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class ShortPrimitive2DArrayTestClass {
        private short[][] a;
    }

    /**
     *  Byte
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class BytePrimitiveTestClass {
        byte a;
        volatile byte b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class ByteNonPrimitiveTestClass {
        Byte a;
        volatile Byte b;
    }

    @Data
    @EqualsAndHashCode
    public static class BytePrimitiveArrayTestClass {
        private byte[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class BytePrimitive2DArrayTestClass {
        private byte[][] a;
    }

    /**
     *  Boolean
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class BooleanPrimitiveTestClass {
        boolean a;
        volatile boolean b;
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class BooleanNonPrimitiveTestClass {
        Boolean a;
        volatile Boolean b;
    }

    @Data
    @EqualsAndHashCode
    public static class BooleanPrimitiveArrayTestClass {
        private boolean[] a;
    }

    @Data
    @EqualsAndHashCode
    public static class BooleanPrimitive2DArrayTestClass {
        private boolean[][] a;
    }

    /**
     *  String
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class StringTestClass {
        String a;
        volatile String b;
    }

    @Data
    @AllArgsConstructor
    public static class StringBuilderTestClass {
        StringBuilder a;
        volatile StringBuilder b;

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
    @AllArgsConstructor
    public static class StringBufferTestClass {
        StringBuffer a;
        volatile StringBuffer b;

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

    @Data
    @EqualsAndHashCode
    public static class String2DArrayTestClass {
        private String[][] a;
    }

    /**
     *  Enums
     */

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class EnumTestClass {
        SomeEnum a;
        volatile SomeEnum b;

        public enum SomeEnum {
            A,
            B,
            C
        }
    }

    /**
     *
     */
    public static class NullClass {

        String aString;
        StringBuffer aStringBuffer;
        String[] aStringArray = new String[10];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NullClass)) return false;

            NullClass nullClass = (NullClass) o;

            if (aString == null && nullClass.aString == null)
                return true;

            if (aStringBuffer == null && nullClass.aStringBuffer == null)
                return true;

            if (aStringArray == null && nullClass.aStringArray == null)
                return true;

            if (aString != null ? !aString.equals(nullClass.aString) : nullClass.aString != null) return false;

            if (aStringBuffer != null ? !aStringBuffer.toString().equals(nullClass.aStringBuffer.toString()) : nullClass.aStringBuffer != null) return false;

            if (aStringArray.length != nullClass.aStringArray.length) return false;

            for (int i=0; i<aStringArray.length; i++) {
                if (!aStringArray[i].equals(nullClass.aStringArray[i])) return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            return aString != null ? aString.hashCode() : 0;
        }
    }

    /**
     *
     */
    public static class SerializableClass extends SuperClass {

        private int anInt;
        private String aString;
        private Long aWrapperLong;
        private StringBuffer aStringBuffer;
        private StringBuilder aStringBuilder;
        private String[] aStringArray;
        private volatile Weekdays day;
        private BigInteger aBigInteger;

        public static SerializableClass createAnObject() {
            SerializableClass serializableClass = new SerializableClass();
            serializableClass.someInt = 10;
            serializableClass.anInt = 100;
            serializableClass.aString = "Ämir & Daniel";
            serializableClass.aWrapperLong = 2L;
            serializableClass.aBigInteger = new BigInteger("13231234353945873458998734598729879879872348723487234");
            serializableClass.aStringBuffer = new StringBuffer(serializableClass.aString);
            serializableClass.aStringBuilder = new StringBuilder(serializableClass.aString);
            serializableClass.aStringArray = new String[4];
            for (int i = 0; i < 4; i++) {
                serializableClass.aStringArray[i] = serializableClass.aString;
            }
            serializableClass.day = Weekdays.FRIDAY;

            return serializableClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SerializableClass)) return false;

            SerializableClass that = (SerializableClass) o;

            if (day != that.day) return false;
            if (anInt != that.anInt) return false;
            if (!aBigInteger.equals(that.aBigInteger)) return false;
            if (aString != null ? !aString.equals(that.aString) : that.aString != null) return false;
            if (!Arrays.equals(aStringArray, that.aStringArray)) return false;
            if (aStringBuffer != null ? !aStringBuffer.toString().equals(that.aStringBuffer.toString()) : that.aStringBuffer != null)
                return false;

            if (aStringBuilder != null ? !aStringBuilder.toString().equals(that.aStringBuilder.toString()) : that.aStringBuilder != null)
                return false;

            if (aWrapperLong != null ? !aWrapperLong.equals(that.aWrapperLong) : that.aWrapperLong != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + anInt;
            result = 31 * result + (aString != null ? aString.hashCode() : 0);
            result = 31 * result + (aWrapperLong != null ? aWrapperLong.hashCode() : 0);
            result = 31 * result + (aStringBuffer != null ? aStringBuffer.hashCode() : 0);
            result = 31 * result + (aStringBuilder != null ? aStringBuilder.hashCode() : 0);
            result = 31 * result + (aStringArray != null ? Arrays.hashCode(aStringArray) : 0);
            return result;
        }

        public enum Weekdays {
            SATURDAY,
            SUNDAY,
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY
        }
    }

    /**
     *
     */
    @Data
    @EqualsAndHashCode
    public static class DateTestClass {
        private Date a;
    }


}
