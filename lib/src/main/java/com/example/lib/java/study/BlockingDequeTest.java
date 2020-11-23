package com.example.lib.java.study;

import com.example.lib.java.BasicJavaTestCode;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * http://tutorials.jenkov.com/java-util-concurrent/linkedblockingdeque.html
 * LinkedBlockingDeque
 */
public class BlockingDequeTest extends BasicJavaTestCode {
    public static void main(String[] args) {

        BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

        deque.addFirst("1");
        deque.addLast("2");

        try {
            String two = deque.takeLast();
            String one = deque.takeFirst();

            println("two >>> " + two);
            println("one >>> " + one);
            println("deque size >>> " + deque.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
