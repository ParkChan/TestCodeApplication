package com.example.lib.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCode1 {

    public static void main(String[] args) {
        System.out.println("result sum is >> " + getSum(12345));
    }

//    public int[] solution(int[] array, int[][] commands) {
//        int[] answer = {};
//
//        int startIndex = array[0];
//        int endIndex = array[1];
//        int selectIndex = array[1];
//
//
//
//        for (int i = 0; i < commands.length; i++) {
//
//            Integer[] temp = new Integer[array[1] - array[0]];
//
//            for (int j = 0; j < commands[i].length; j++) {
//                int tempPosition = 0;
//                for (int k = 0; k < commands[i][j]; k++) {
//                    int value = commands[i][j];
//                    if (value >= startIndex && value <= endIndex) {
//                        temp[tempPosition] = value;
//                        tempPosition++;
//                    }
//                }
//            }
//            List<Integer> numList = Arrays.asList(temp);
//            Collections.sort(numList);
//            numList.get(selectIndex);
//
//            System.out.println(numList);
//        }
//        return answer;
//    }
//

    /**
     * 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
     * 예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
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
}
