package com.mojang.blixtser.core;

public class UnknownRegisteredTypeException extends RuntimeException {

    public UnknownRegisteredTypeException(String name) {
        super("Did not find a registered type for class '" + name + "'");
    }

    public UnknownRegisteredTypeException(String name, String type) {
        super("Did not find a registered type for field '" + name + "' with type '" + type + "'");
    }
}
