package org.example.bytecodes;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface test = IoC.createMyClass();
        test.calc(10, 7);
        test.calc(3, 17, 5);
    }
}