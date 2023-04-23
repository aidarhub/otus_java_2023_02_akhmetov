package org.example.hw;

public class TestMethods {

    public static void addition(int a, int b) {
        int c = a + b;
        System.out.println(a + " + " + b + " = " + c);
    }

    public static void subtraction(int a, int b) {
        if (a > b) {
            int c = a - b;
            System.out.println(a + " - " + b + " = " + c);
        } else {
            throw new RuntimeException();
        }
    }

    public static void multiplication(int a, int b) {
        int c = a * b;
        System.out.println(a + " * " + b + " = " + c);
    }
}
