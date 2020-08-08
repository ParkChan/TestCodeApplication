package com.example.lib.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class TestCode {

    public static void main(String[] args) {

//        System.out.println("result sum is >> " + getSum(12345));

//        String[] phoneBook1 = {"119", "11997674223", "1195524421"};
//        phoneNumber(phoneBook1);


//        int[] prices = {1, 2, 3, 2, 3};
//        System.out.println("solution is >> " + Arrays.toString(solution(prices)));

        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        System.out.println("solution is >> " + Arrays.toString(solution(progresses, speeds)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add((100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i]
                    : (100 - progresses[i]) / speeds[i] + 1
            );
        }
        System.out.println("queue is >> " + queue.toString());
        List<Integer> result = new ArrayList<>();
        int compareNum = queue.poll();
        int updateCnt = 1;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (compareNum >= num) {
                updateCnt++;
            } else {
                result.add(updateCnt);
                updateCnt = 1;
                compareNum = num;
            }
        }
        result.add(updateCnt);
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }


    public static int[] solution(int[] prices) {

        int[] answer = new int[prices.length];
        int count = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            count = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] <= prices[j]) {
                    count++;
                } else {
                    count++;
                    break;
                }
            }
            answer[i] = count;
        }

        answer[prices.length - 1] = 0;
        return answer;

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
