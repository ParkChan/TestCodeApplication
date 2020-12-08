package com.example.lib.java.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [출처] 프로그래머스
 * <p>
 * [문제]
 * 완주하지 못한 선수
 * 문제 설명
 * 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
 * <p>
 * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한사항
 * 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
 * completion의 길이는 participant의 길이보다 1 작습니다.
 * 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
 * 참가자 중에는 동명이인이 있을 수 있습니다.
 * <p>
 * 입출력 예
 * participant                                  completion                                  return
 * [leo, kiki, eden]                            [eden, kiki]                                 leo
 * [marina, josipa, nikola, vinko, filipa]	    [josipa, filipa, marina, nikola]            vinko
 * [mislav, stanko, mislav, ana]	            [stanko, ana, mislav]                       mislav
 */
public class Marathon {

    public static void main(String[] args) {
        println("===========");
        println("Hello World");
        println("===========");

        String[] participant = {"eden", "eden", "leo", "kiki"};
        String[] completion = {"leo", "kiki", "eden"};

        println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {

        Map<String, Integer> map = new HashMap<>();
        // 참가자의 이름별 명단을 맵에 담음
        for (String p:participant)  {
            if(map.get(p) != null){
                map.put(p, map.get(p) + 1);
            }else{
                map.put(p,1);
            }
        }
        // 완주자의 리스트 만큼 명단에서 cnt 를 낮춤
        for (String c:completion){
            if(map.get(c) != 0) {
                map.put(c, map.get(c) - 1);
            }
        }
        // map 중 값이 0이 아닌 key 만 answer 에 담음
        List<String> list = new ArrayList<>();
        Iterator<String> keys = map.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
            if(map.get(key) != 0) {
                for(int i = 0; i < map.get(key); i++) {
                    list.add(key);
                }
            }
        }
        String answer = list.get(0);
        return answer;
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void println(String message) {
        System.out.println(message);
    }

}