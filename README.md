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
- [Supported Types](#supported-types)
- [Benchmark](#benchmark)

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


## Benchmark

We have micro-benchmarked Blixtser against [Kryo](https://github.com/EsotericSoftware/kryo),
[fast-serialization](https://code.google.com/p/fast-serialization/) and Java built-in serializer using
[JMH](http://openjdk.java.net/projects/code-tools/jmh/) library.

A sample micro-benchmark on a OSx 10.9.1 (3 GHz Intel Core i7, L2 Cache 256 KB, L3 Cache 4 MB):

```
Benchmark                    Mode   Samples         Mean   Mean error    Units
------------------------------------------------------------------------------
blixtser                    thrpt        10        0.008        0.001   ops/ms
fastSerializer              thrpt        10        0.006        0.001   ops/ms
java_built_in_serializer    thrpt        10        0.000        0.000   ops/ms
kryo                        thrpt        10        0.001        0.000   ops/ms
```

```
Benchmark                    Mode   Samples         Mean   Mean error    Units
------------------------------------------------------------------------------
blixtser                     avgt        10      140.400       26.830    ms/op
fastSerializer               avgt        10      168.821       40.259    ms/op
java_built_in_serializer     avgt        10     5059.678     1479.748    ms/op
kryo                         avgt        10     1861.074       51.842    ms/op
```

```
Benchmark                    Mode   Samples         Mean   Mean error    Units
------------------------------------------------------------------------------
blixtser                   sample        80      134.373        2.383    ms/op
fastSerializer             sample        68      160.818        3.978    ms/op
java_built_in_serializer   sample        10     4985.350      295.715    ms/op
kryo                       sample        10     1663.461      324.640    ms/op
```

```
Benchmark                    Mode   Samples         Mean   Mean error    Units
------------------------------------------------------------------------------
blixtser                       ss        10      109.216        5.472       ms
fastSerializer                 ss        10      139.314       34.530       ms
java_built_in_serializer       ss        10     4377.815      361.201       ms
kryo                           ss        10     1598.865       34.958       ms
```