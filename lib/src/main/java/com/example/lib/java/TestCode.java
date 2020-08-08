package com.example.lib.java;

import java.util.Arrays;

public class TestCode {

    public static void main(String[] args) {

//        System.out.println("result sum is >> " + getSum(12345));

//        String[] phoneBook1 = {"119", "11997674223", "1195524421"};
//        phoneNumber(phoneBook1);


    }


    public static boolean phoneNumber(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            for (int j = i + 1; j < phone_book.length; j++) {
                if (phone_book[i].startsWith(phone_book[j])) {
                    return false;
                }
                if (phone_book[j].startsWith(phone_book[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getSum(int n) {
        int sum = 0;
        String number = String.valueOf(n);
        for (int i = 0; i < number.length(); i++) {
            sum = sum + Character.getNumericValue(number.charAt(i));
        }
        return sum;
    }
}
