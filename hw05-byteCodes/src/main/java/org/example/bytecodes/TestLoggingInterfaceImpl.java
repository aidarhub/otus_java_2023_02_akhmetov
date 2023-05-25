package org.example.bytecodes;

public class TestLoggingInterfaceImpl implements TestLoggingInterface{

    @Override
    @Log
    public void calc(int a, int b) {
        int c = a + b;
        System.out.println(a + " + " + b + " = " + c);
    }

    @Override
    @Log
    public void calc(int a, int b, int c) {
        int d = a + b + c;
        System.out.println(a + " + " + b + " + " + c + " = " +  d);
    }

    @Override
    public String toString() {
        return "TestLoggingInterfaceImpl{}";
    }
}
