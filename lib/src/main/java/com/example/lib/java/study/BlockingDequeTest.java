package com.example.lib.java.study;

import com.example.lib.java.BasicJavaTestCode;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * http://tutorials.jenkov.com/java-util-concurrent/linkedblockingdeque.html
 * LinkedBlockingDeque
 * Deque라는 단어는 "Double End Queue"라는 용어에서 왔다. 따라서 Deque는 대기열의 양 끝에 요소를 삽입하고 제거할 수 있는 대기열이다.
 * LinkedBlockingDeque는 스레드가 비어 있는 상태에서 요소를 빼내려고 하면 스레드가 어떤 끝에서 요소를 빼내려고 하는지에 관계없이 차단하는 Deque이다.
 */
public class BlockingDequeTest extends BasicJavaTestCode {
    public static void main(String[] args) {

        BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

        deque.addFirst("1");
        deque.addLast("2");

        try {
            String two = deque.takeLast();
            String one = deque.takeFirst();

            print("two >>> " + two);
            print("one >>> " + one);
            print("deque size >>> " + deque.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
