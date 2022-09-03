package com.hyve.app.setup;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class SpacedDisplayNameGenerator implements DisplayNameGenerator {

    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {
        return aClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> aClass) {
        return aClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> aClass, Method method) {
        return splitCamelCase(method.getName()).toLowerCase();
    }

    static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }
}
