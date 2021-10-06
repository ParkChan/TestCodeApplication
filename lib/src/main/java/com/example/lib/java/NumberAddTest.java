package com.example.lib.java;

public class NumberAddTest {

    public static void main(String[] args) {
        println("===========");
        println("result sum is >> " + getSum(12345));
        print("===========");
    }

    /**
     * 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
     * 예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
     *
     * @return
     */
    public static int getSum(int n) {
        int sum = 0;
        String number = String.valueOf(n);
        for (int i = 0; i < number.length(); i++) {
            sum = sum + Character.getNumericValue(number.charAt(i));
        }
        return sum;
    }

    private static void print(String message){
        System.out.print(message);
    }
    private static void println(String message){
        System.out.println(message);
    }
}
