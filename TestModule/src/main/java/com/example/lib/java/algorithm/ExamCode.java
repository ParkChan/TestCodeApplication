package com.example.lib.java.algorithm;

import java.util.HashMap;

/*
 * Verbal memory 게임에서 게이머는 각 라운드마다 주어진 단어가 몇 번째 라운드에서 나왔던 단어인지 판단해야 합니다.
 * 예를 들어, 4 라운드 게임에서 각 라운드마다 단어가 다음같이 주어진 경우, [one, one, two, one]
 *
 * 1 라운드: 단어 one이 주어졌으며, 이 단어는 이번 라운드에 처음으로 등장한 단어입니다.
 * 2 라운드: 단어 one이 주어졌으며, 이 단어는 1 라운드에서 처음으로 등장했습니다.
 * 3 라운드: 단어 two가 주어졌으며, 이 단어는 이번 라운드에 처음으로 등장한 단어입니다.
 * 4 라운드: 단어 one이 주어졌으며, 이 단어는 1 라운드에서 처음으로 등장했습니다.
 * 따라서 각 라운드에 주어진 단어는 [1, 1, 3, 1] 라운드에 처음으로 등장했습니다.
 *
 * 각 라운드마다 주어진 단어를 순서대로 저장한 배열이 주어질 때, 해당 단어가 몇 라운드에서 처음 등장했는지 리턴하는 함수, solution을 작성하세요.
 *
 * 제한 조건
 * 한 게임은 1 라운드 이상, 200,000 라운드 이하로 구성됩니다.
 * 라운드마다 주어지는 단어는 영문 소문자로만 구성됩니다.
 * 라운드마다 주어지는 단어의 길이는 1 이상, 30 이하입니다.
 * 입출력 예
 * words	return
 * [one, one, two, one]	[1, 1, 3, 1]
 * [may, with, may, you, you]	[1, 2, 1, 4, 4]
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 앞서 설명한 예시와 같습니다.
 *
 * 입출력 예 #2
 *
 * 라운드 수	주어진 단어	결과	결과 이유
 * 1	may	1	처음 등장한 단어
 * 2	with	2	처음 등장한 단어
 * 3	may	1	1 라운드에 나온 단어
 * 4	you	4	처음 등장한 단어
 * 5	you	4	4 라운드에 나온 단어
 */
public class ExamCode {

    public static void main(String[] args) {

        String[] words = {"one", "one", "two", "one"};
        println("===========");

        long[] test = solution(words);
        for (int i = 0; i < test.length; i++) {
            print(test[i] + " ");
        }
        println("===========");
    }

    public static long[] solution(String[] words) {
        long[] answer = new long[words.length];
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String compareWord = words[i];
            if(hashMap.get(compareWord) == null){
                hashMap.put(compareWord, i + 1);
                answer[i] = i + 1;
            }else{
                answer[i] = hashMap.get(compareWord);
            }
        }
        return answer;
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void println(String message) {
        System.out.println(message);
    }
}