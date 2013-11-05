package com.mojang.serialization;

import java.util.Arrays;

class SerializableClass extends SuperClass {

    private int anInt;
    private String aString;
    private Long aWrapperLong;
    private StringBuffer aStringBuffer;
    private StringBuilder aStringBuilder;
    private String[] aStringArray;
    private Weekdays day;

    public static SerializableClass createAnObject() {
        SerializableClass serializableClass = new SerializableClass();
        serializableClass.someInt = 10;
        serializableClass.anInt = 100;
        serializableClass.aString = "Amir & Daniel";
        serializableClass.aWrapperLong = 2L;
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
        if (aString != null ? !aString.equals(that.aString) : that.aString != null) return false;
        if (!Arrays.equals(aStringArray, that.aStringArray)) return false;
        if (aStringBuffer != null ? !aStringBuffer.toString().equals(that.aStringBuffer.toString()) : that.aStringBuffer != null)
            return false;

        if (aStringBuilder != null ? !aStringBuilder.toString().equals(that.aStringBuilder.toString()) : that.aStringBuilder != null)
            return false;

        if (aWrapperLong != null ? !aWrapperLong.equals(that.aWrapperLong) : that.aWrapperLong != null) return false;

        if (aStringArray == null && that.aStringArray == null) {
            return true;
        }

        if (aStringArray == null || that.aStringArray == null) {
            return false;
        }

        if (aStringArray.length != that.aStringArray.length) {
            return false;
        }

        for (int i = 0; i < aStringArray.length; i++) {
            if (!aStringArray[i].equals(that.aStringArray[i]))
                return false;
        }


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
