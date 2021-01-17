package com.example.lib.java.study;

import com.example.lib.java.util.CustomLogger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://wakestand.tistory.com/198
 */
public class QueueTest {

    public static void main(String[] args) {

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

        Queue<String> queue = new LinkedList<>();
        //offer 메서드는 큐가 가득차서 더이상 추가할 수 없는 경우 false를 반환하고 요소가 추가되면 true를 반환하며 특정 예외를 throw하지 않는다.
        //add 메소드는 illegalStateException를 발생시킨다.

        for (String s : alphabet) {
            //큐안에 값 넣기
            queue.offer(s);
        }

        CustomLogger.getInstance().info("Queue 값 포함 여부 : " + queue.contains("B"));
        CustomLogger.getInstance().info("Queue 값 포함 다음 출력값 확인하기 : " + queue.peek());
        CustomLogger.getInstance().info("Queue 크기확인: " + queue.size());
        CustomLogger.getInstance().info("Queue 안의 특정값 빼기: " + queue.remove("A"));
        CustomLogger.getInstance().info("Queue 크기확인: " + queue.size());

        for (int i = 0; i < queue.size();) {
            //Queue 안의 값 꺼내기
            String name = queue.poll();
            CustomLogger.getInstance().info(name);
        }
        CustomLogger.getInstance().info("Queue가 비었는가? " + queue.isEmpty());
        queue.clear();
        CustomLogger.getInstance().info("Clear이후 Queue가 비었는가? " + queue.isEmpty());
    }
}
