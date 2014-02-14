package com.mojang.blixtser.core;

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
    public static class EnumTestClass {
        private SomeEnum a;

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
