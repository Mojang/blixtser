package com.mojang.blixtser.core;

public class UnknownRegisteredTypeException extends RuntimeException {

    public UnknownRegisteredTypeException(String name) {
        super("Did not find a registered type for class '" + name + "'. Did you forget to register the class?");
    }

}
