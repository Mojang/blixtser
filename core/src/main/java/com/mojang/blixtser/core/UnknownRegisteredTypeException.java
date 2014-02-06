package com.mojang.blixtser.core;

public class UnknownRegisteredTypeException extends RuntimeException {

    public UnknownRegisteredTypeException(String tp) {
        super("Did not find a registered type for " + tp);
    }
}