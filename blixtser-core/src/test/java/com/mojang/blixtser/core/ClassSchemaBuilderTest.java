package com.mojang.blixtser.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClassSchemaBuilderTest {

    private ClassSchemaBuilder classSchemaBuilder = new ClassSchemaBuilder();

    @Test
    public void super_class() {
        ClassSchemaBuilder.ClassInfo classInfo = classSchemaBuilder.createClassInfo(TestClass.class);
        Assert.assertEquals(7, classInfo.fieldInfos.length);
    }

    @Test
    public void mergable_fields() {
        ClassSchemaBuilder.ClassInfo classInfo = classSchemaBuilder.createClassInfo(ClassWithMergeableFields1.class);
        Assert.assertEquals(1, classInfo.fieldInfos.length);
    }

    @Test
    public void mergable_fields_and_super_class() {
        ClassSchemaBuilder.ClassInfo classInfo = classSchemaBuilder.createClassInfo(ClassWithMergeableFields2.class);
        Assert.assertEquals(1, classInfo.fieldInfos.length);
    }

    @Test
    public void mergable_fields_and_super_class_and_volatile() {
        ClassSchemaBuilder.ClassInfo classInfo = classSchemaBuilder.createClassInfo(ClassWithMergeableFields3.class);
        Assert.assertEquals(4, classInfo.fieldInfos.length);
    }

    @Test
    public void unknown_fields() {
        ClassSchemaBuilder.ClassInfo classInfo = classSchemaBuilder.createClassInfo(ClassWithUnknownFieldTypes.class);
        Assert.assertEquals(2, classInfo.fieldInfos.length);
    }

    @Test
    public void abstract_class_test() {
        try {
            classSchemaBuilder.createClassInfo(AbstractClass.class);
            Assert.fail();
        } catch (RuntimeException e) {}
    }

    @Test
    public void interface_class_test() {
        try {
            classSchemaBuilder.createClassInfo(InterfaceClass.class);
            Assert.fail();
        } catch (RuntimeException e) {}
    }

    /**
     *
     */
    private static interface InterfaceClass {

    }

    /**
     *
     */
    private static abstract class AbstractClass {
        private int a;
    }

    /**
     *
     */
    private static class ClassWithMergeableFields1 {
        private int a, b, c, d, e;
        private long f, g, h;
        private double i, j, k, l, m, n, o;
        private float p, q, r;
        private byte s, t, u;
        private short v, w;
        private boolean x, y, z;
    }

    /**
     *
     */
    private static class ClassWithMergeableFields2 extends ClassWithMergeableFields1 {
        private int a, b, c, d, e;
        private long f, g, h;
        private double i, j, k, l, m, n, o;
        private float p, q, r;
        private byte s, t, u;
        private short v, w;
        private boolean x, y, z;
    }

    /**
     *
     */
    private static class ClassWithMergeableFields3 extends ClassWithMergeableFields2 {
        private volatile int v1, v2;
        private String a;
    }


    /**
     *
     */
    private static class TestSuperClass {
        private int a1;
        private Integer a2;
    }

    /**
     *
     */
    private static class TestClass extends TestSuperClass {
        private String b;
        private double c1;
        private Double c2;
        private float d1;
        private Float d2;
        private short e1;
        private Short e2;
    }

    /**
     *
     */
    private class ClassWithUnknownFieldTypes {
        private List<String> collections;
        private volatile List<String> volatileCollections;
        private int size;
        private String name;
    }
}
