package com.example.lib.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ExamCode {

    public static void main(String[] args) {

//        final String[] list = {"3285-3764-9934-2453", "3285376499342453", "3285-3764-99342453", "328537649934245", "3285376499342459", "3285-3764-9934-2452", "3285-1111-1111-1111"};
//        System.out.println(Arrays.toString(solution(list)));

        final int[] list = {1, 3, 7, 8, 10, 15};
        System.out.println(solution(list, 2));
    }


    public static int solution(int[] scores, int k) {
        int answer = 5;
        int groupCnt = scores.length % k;

        int indexCount = 1;
        List<List<Integer>> mainList = new ArrayList<>();
        ArrayList<Integer> subList = new ArrayList<>();
        mainList.add(subList);

        for (int i = 0; i < scores.length; i++) {

            subList.add(scores[i]);

            if (i + 1 % k == 0) {
                subList = new ArrayList<>();
                mainList.add(subList);
            }
            indexCount++;
        }
        System.out.println("test >>> " + mainList.toString());

        return answer;
    }

    public static int[] solution(String[] card_numbers) {
        int[] answer = new int[card_numbers.length];

        for (int i = 0; i < card_numbers.length; i++) {
            String number = card_numbers[i];
            if (card_numbers[i].contains("-")) {
                if(card_numbers[i].length() == 19){
                    String[] numberArr = number.split("-");
                    int innerCount = 0;
                    for (String s : numberArr) {
                        if (s.length() == 4) {
                            innerCount++;
                        }
                    }
                    if (innerCount == 4 && numberArr.length == 4) {
                        answer[i] = 1;
                    } else {
                        answer[i] = 0;
                    }
                }else{
                    answer[i] = 0;
                }

            } else {
                if (number.length() == 16) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }
            }
        }

        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == 1) {
                int sum = 0;
                String cardNum = card_numbers[i].replace("-", "");
                for (int j = cardNum.length() - 1; j >= 0; j--) {
                    if (j % 2 == 0) {
                        //3285376499342453
                        int temp = Character.getNumericValue(cardNum.charAt(j)) * 2;
                        if (temp >= 10) {
                            String strTempSum = String.valueOf(temp);
                            int tempSum = Character.getNumericValue(strTempSum.charAt(0))
                                    + Character.getNumericValue(strTempSum.charAt(1));
                            sum = sum + tempSum;
                        } else {
                            sum = sum + temp;
                        }
                    } else {
                        sum = sum + Character.getNumericValue(cardNum.charAt(j));

                    }
                }
                if (sum % 10 == 0) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }
            }
        }
        return answer;
    }

}
