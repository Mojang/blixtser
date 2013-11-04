package com.mojang.serialization;

class SerializableClass extends SuperClass {

    private int anInt;
    private String aString;
    private Long aWrapperLong;

    public static SerializableClass createAnObject() {
        SerializableClass serializableClass = new SerializableClass();
        serializableClass.someInt = 10;
        serializableClass.anInt = 100;
        serializableClass.aString = "Amir & Daniel";
        serializableClass.aWrapperLong = 2L;
        return serializableClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SerializableClass)) return false;

        SerializableClass that = (SerializableClass) o;

        if (someInt != that.someInt) return false;
        if (anInt != that.anInt) return false;
        if (aString != null ? !aString.equals(that.aString) : that.aString != null) return false;
        if (aWrapperLong != null ? !aWrapperLong.equals(that.aWrapperLong) : that.aWrapperLong != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = anInt;
        result = 31 * result + someInt;
        result = 31 * result + (aString != null ? aString.hashCode() : 0);
        result = 31 * result + (aWrapperLong != null ? aWrapperLong.hashCode() : 0);
        return result;
    }
}
