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
- `Date`


## Benchmark

We have micro-benchmarked Blixtser against [Kryo](https://github.com/EsotericSoftware/kryo),
[fast-serialization](https://code.google.com/p/fast-serialization/) and Java built-in serializer using
[JMH](http://openjdk.java.net/projects/code-tools/jmh/) library.

A sample micro-benchmark on a OSx 10.10 (3 GHz Intel Core i7, L2 Cache 256 KB, L3 Cache 4 MB):

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                     thrpt       10  7,804 ? 0,711  ops/s
fastSerializer               thrpt       10  5,041 ? 0,233  ops/s
java_built_in_serializer     thrpt       10  0,324 ? 0,008  ops/s
kryo                         thrpt       10  4,607 ? 0,311  ops/s

```

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                      avgt       10  0,117 ? 0,011   s/op
fastSerializer                avgt       10  0,208 ? 0,024   s/op
java_built_in_serializer      avgt       10  3,109 ? 0,129   s/op
kryo                          avgt       10  0,261 ? 0,061   s/op
```

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                    sample       96  0,124 ? 0,007   s/op
fastSerializer              sample       56  0,224 ? 0,021   s/op
java_built_in_serializer    sample       10  3,649 ? 0,460   s/op
kryo                        sample       58  0,219 ? 0,012   s/op
```

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                        ss       10  0,151 ? 0,058      s
fastSerializer                  ss       10  0,284 ? 0,163      s
java_built_in_serializer        ss       10  3,248 ? 0,206      s
kryo                            ss       10  0,226 ? 0,007      s
```

### How to run the benchmarks

Simply cd to `blixtser-benchmark` and run the following:

```bash
$ gradle -PbenchmarkName=com.mojang.blixtser.benchmark.MicroBenchmark_AllModes benchmark
```

## License

Distributed under the [MIT License](https://github.com/Mojang/blixtser/blob/master/LICENSE.md)
