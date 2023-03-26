package org.example;

import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        int a1 = 10;
        int b1 = 390;
        int ans1 = IntMath.checkedSubtract(a1, b1);
        System.out.println("Difference of " + a1 + " and " + b1 + " is: " + ans1);

        int a2 = 339;
        int b2 = 777;
        int ans2 = IntMath.checkedSubtract(a2, b2);
        System.out.println("Difference of " + a2 + " and " + b2 + " is: " + ans2);

        List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> reverse = Lists.reverse(myList);
        System.out.println(reverse);
    }
}
