package org.example.hw;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelper {

    public ReflectionHelper() {
    }

    public static List<Method> getAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)){
                list.add(method);
            }
        }
        return list;
    }

    public static Object getFieldValue(Object object, String name) {
        try {
            var field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setFieldValue(Object object, String name, Object value) {
        try {
            var field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object callMethod(Object object, Method method, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }
}
