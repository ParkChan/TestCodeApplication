package com.example.lib.java.algorithm;

/**
 * 문제 설명
 * 글자 n개를 보여주는 LED 전광판이 있습니다. 이 전광판에 글자를 오른쪽에서 왼쪽으로 반복해 흘러가게 만들려합니다.
 * <p>
 * 예를 들어, 6글자를 표시할 수 있는 전광판에 hi bye를 작성하면 전광판은 다음과 같이 변합니다.
 * <p>
 * 처음(0초)
 * <p>
 * LEDboard_1.png
 * <p>
 * 1초 후
 * <p>
 * LEDboard_2.png
 * <p>
 * 2초 후
 * <p>
 * LEDboard_3.png
 * <p>
 * 6초 후
 * <p>
 * LEDboard_4.png
 * <p>
 * 11초 후
 * LEDboard_5.png
 * <p>
 * 12초 후
 * <p>
 * LEDboard_6.png
 * <p>
 * 13초 후
 * <p>
 * LEDboard_7.png
 * <p>
 * 전광판에 표시할 수 있는 글자 수 n, 전광판에 표시할 문구 text와 시간 second가 매개변수로 주어질 때, second 초가 지난 후 전광판에 나오는 문구를 리턴하는 solution 함수를 작성해 주세요.
 * <p>
 * 제한 사항
 * n은 1 이상 10 이하인 자연수입니다.
 * text는 길이가 1 이상 30 이하인 문자열입니다.
 * text는 알파벳 소문자와 공백(" ")으로 이루어집니다.
 * second는 1 이상 1000 이하인 자연수입니다.
 * 리턴시 공백은 _ 로 나타내주세요.
 * 입출력 예
 * n	text	second	result
 * 6	hi bye	1	_____h
 * 6	hi bye	2	____hi
 * 6	hi bye	6	hi_bye
 * 입출력 예 설명
 * 앞서 설명한 예와 같습니다.
 */
public class ExamCode2 {

    public static void main(String[] args) {

//        String[] words = {"one", "one", "two", "one"};
//        println("===========");
//        print(solution(6, "hi bye", 6));
    }

    /**
     * @param n      전광판에 표시할 수 있는 굴자수
     * @param text   문구
     * @param second 시간
     * @return 전광판 문구
     */
    public static String solution(int n, String text, int second) {
        String answer = "";
        StringBuilder builder = new StringBuilder();
        String visibleWord = text.substring(0, second);
        int totalWordCount = n;
        for (int position = 0; position < n; position++) {
            if (totalWordCount > visibleWord.length()) {
                builder.append("_");
                totalWordCount--;
            }
        }
        builder.append(visibleWord.replace(" ", "_"));
        answer = builder.toString();
        return answer;
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void println(String message) {
        System.out.println(message);
    }
}