package com.mojang.serialization;

public class NullClass {

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
