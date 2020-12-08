package com.example.lib.java.study;

import com.example.lib.java.BasicJavaTestCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.rmi.runtime.Log;

/**
 * https://wakestand.tistory.com/198
 */
public class QueueTest extends BasicJavaTestCode {

    public static void main(String[] args) {

        initLogger(false);

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

        Queue<String> queue = new LinkedList<>();
        //offer 메서드는 큐가 가득차서 더이상 추가할 수 없는 경우 false를 반환하고 요소가 추가되면 true를 반환하며 특정 예외를 throw하지 않는다.
        //add 메소드는 illegalStateException를 발생시킨다.

        for (String s : alphabet) {
            queue.offer(s);
        }

        print("Queue 값 포함 여부 : " + queue.contains("B"));
//        println("Queue 값 포함 다음 출력값 확인 1: " + queue.peek());
//        println("Queue 크기확인: " + queue.size());
//        println("Queue 값 포함 다음 출력값 확인 2: " + queue.peek());
//        println("Queue 크기확인: " + queue.size());


    }
}
