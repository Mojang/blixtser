package com.mojang.blixtser.core;

import com.mojang.blixtser.core.ClassSchemaBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class ClassSchemaBuilderTest {

    private ClassSchemaBuilder classSchemaBuilder = new ClassSchemaBuilder();

    @Test
    public void test_with_super_class() {
        ClassSchemaBuilder.ClassInfo classInfo = classSchemaBuilder.createClassInfo(SerializableClass.class, new HashSet<String>());
        Assert.assertEquals(7, classInfo.fieldInfos.length);
    }



}
