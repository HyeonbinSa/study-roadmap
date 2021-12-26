package com.benworld.javastudy.lambda;

interface PrintString {
    void showString(String str);
}

public class TestLambda {
    public void main(String[] args) {
        PrintString lambdaStr = s -> System.out.println(s);
        lambdaStr.showString("Test");

        showMyString(lambdaStr);

        PrintString lambdaTest = returnString();
        lambdaTest.showString("Test3");
    }

    public static void showMyString(PrintString p) {
        p.showString("Test2");
    }

    public static PrintString returnString() {
        return s->System.out.println(s+"!!!");
    }
}
