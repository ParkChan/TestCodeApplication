package com.example.lib.java;

public class BasicJavaTestCode {

    public static void main(String[] args) {
        println("===========");
        println("Hello World");
        println("===========");

        int a = 5;

        if (a > 5) {
            println("===========1");
        } else if (a > 4) {
            println("===========2");
        } else if (a > 3) {
            println("===========3");
        } else {
            println("===========4");
        }
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void println(String message) {
        System.out.println(message);
    }

    public static long[] solution(String[] words) {
        long[] answer = {};
        return answer;
    }
}