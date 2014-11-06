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
blixtser                     thrpt       10  8,214 ? 0,647  ops/s
fastSerializer               thrpt       10  4,465 ? 0,175  ops/s
java_built_in_serializer     thrpt       10  0,294 ? 0,008  ops/s
kryo                         thrpt       10  4,382 ? 0,249  ops/s
```

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                      avgt       10  0,123 ? 0,011   s/op
fastSerializer                avgt       10  0,217 ? 0,010   s/op
java_built_in_serializer      avgt       10  3,245 ? 0,110   s/op
kryo                          avgt       10  0,222 ? 0,013   s/op
```

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                    sample       94  0,125 ? 0,006   s/op
fastSerializer              sample       57  0,220 ? 0,005   s/op
java_built_in_serializer    sample       10  3,284 ? 0,066   s/op
kryo                        sample       55  0,226 ? 0,008   s/op
```

```
Benchmark                    Mode   Samples  Score   Error  Units
-----------------------------------------------------------------
blixtser                        ss       10  0,129 ? 0,010      s
fastSerializer                  ss       10  0,212 ? 0,008      s
java_built_in_serializer        ss       10  3,254 ? 0,087      s
kryo                            ss       10  0,233 ? 0,016      s
```

### How to run the benchmarks

Simply cd to `blixtser-benchmark` and run the following:

```bash
$ gradle -PmainClass=com.mojang.blixtser.benchmark.MicroBenchmark_AllModes execute
```

## License

Distributed under the [MIT License](https://github.com/Mojang/blixtser/blob/master/LICENSE.md)
