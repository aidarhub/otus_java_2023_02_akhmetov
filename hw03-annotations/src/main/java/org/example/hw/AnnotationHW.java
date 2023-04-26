package org.example.hw;

import org.example.hw.annotations.After;
import org.example.hw.annotations.Before;
import org.example.hw.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.example.hw.ReflectionHelper.*;

public class AnnotationHW {
    public static void main(String[] args) throws ClassNotFoundException {

        String className = "org.example.hw.MyClass";
        final Class<?> clazz = Class.forName(className);

        final var beforeMethods = getAnnotation(clazz, Before.class);
        final var afterMethods = getAnnotation(clazz, After.class);
        final var testMethods = getAnnotation(clazz, Test.class);

        int failed = 0;
        for (var method : testMethods) {
            Object testObject = instantiate(clazz);
            try {
                runBefore(testObject, beforeMethods);
                callMethod(testObject, method);
            } catch (RuntimeException e) {
                System.out.println("\nTest failed: " + e.getMessage());
                failed++;
            } finally {
                runAfter(testObject, afterMethods);
            }
        }
        System.out.println(getStatistic(testMethods.size(), failed));
    }

    private static void run(final Object object, final List<Method> methods) {
        methods.forEach(method -> callMethod(object, method));
    }

    private static void runBefore(final Object testObject, final List<Method> beforeMethods) {
        run(testObject, beforeMethods);
    }

    private static void runAfter(final Object testObject, final List<Method> afterMethods) {
        run(testObject, afterMethods);
    }

    private static String getStatistic(int total, int failed) {
        int success = total - failed;
        return String.format("\nTest statistics\n\tAll: " + total + "\n\tSuccess: " + success + "\n\tFailed: " + failed);
    }
}
