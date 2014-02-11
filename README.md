## Overview

Blixtser is a fast and efficient serialization tool for Java. The goal for this project is speed, efficiency and minimum
amount of work required to use it in your production environment.

## Disclaimer

Since this project is totally based on using `sun.misc.Unsafe` you should use it with care! You should be extra careful when
it comes to different JVMs and architectures. As a simple example if you serialize something on machine _A_ that has
different endianness and internal representation of native integer than machine _B_ that de-serializes the same thing,
then you are in big trouble!

## Contents

- [Quickstart](#quickstart)
- [Supported Types](#supportedtypes)

## Quickstart

Let's start by checking how to use the library:

```java
Blixtser blixtser = new Blixtser();
blixtser.register(SomeClass.class);
// ...
byte[] serialized = blixtser.serialize(someclass);
SomeClass deserialized = (SomeClass) blixtser.deserialize(serialized);
```

## Supported Types

Blixtser supports the following types out of the box:

- `long`, `long[]`, `long[][]`, `Long`
- `double`, `double[]`, `double[][]`, `Double`
- `float`, `float[]`, `float[][]`, `Float`
- `int`, `int[]`, `int[][]`, `Integer`, `BigInteger`
- `char`, `char[]`, `char[][]`, `Character`
- `short`, `short[]`, `short[][]`, `Short`
- `byte`, `byte[]`, `byte[][]`, `Byte`
- `boolean`, `boolean[]`, `boolean[][]`, `Boolean`
- `String`, `String[]`, `String[][]`, `StringBuffer`, `StringBuilder`
- `Enum`
