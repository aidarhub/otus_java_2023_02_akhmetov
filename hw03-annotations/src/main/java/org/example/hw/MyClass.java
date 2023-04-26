package org.example.hw;

import org.example.hw.annotations.After;
import org.example.hw.annotations.Before;
import org.example.hw.annotations.Test;

public class MyClass {

    @Before
    void before() {
        System.out.println("\nThis is before method");
    }

    @After
    void after() {
        System.out.println("This is after method");
    }

    @Test
    public void getAdd() {
        System.out.println("Test case:");
        TestMethods.addition(25, 8);
    }

    @Test
    public void getSub() {
        System.out.println("Test case:");
        TestMethods.subtraction(10, 3);
    }

    @Test
    public void getMult() {
        System.out.println("Test case:");
        TestMethods.multiplication(2, 15);
    }

    @Test
    public void getException() {
        System.out.println("Test case:");
        TestMethods.subtraction(2, 19);
    }
}
