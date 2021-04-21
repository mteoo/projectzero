package com.matheus.dias.projectzero.core;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

public class Classes {

    public static ImmutableSet<ClassPath.ClassInfo> findAll(String packageName) throws Exception {
        try {
            return ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClassesRecursive(packageName);
        } catch (IOException e) {
            throw new Exception("Erro ao escanear o pacote");
        }
    }

    public static ImmutableSet<ClassPath.ClassInfo> findAllAnnotated(String packageName, Class<? extends Annotation> annotation) throws Exception {
        Set<ClassPath.ClassInfo> classes = findAll(packageName).stream().filter(c -> c.load().isAnnotationPresent(annotation)).collect(Collectors.toSet());
        return ImmutableSet.copyOf(classes);
    }

}
