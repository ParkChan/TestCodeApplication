package com.example.lib.basic.queue

import com.example.lib.java.util.CustomLogger
import org.junit.jupiter.api.Test
import java.util.*

class QueueTest {

    @Test
    fun `큐에 데이터를 삽입하고 출력하는 예제`() {
        val alphabet =
            arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O")

        val queue: Queue<String> = LinkedList()
        //offer 메서드는 큐가 가득차서 더이상 추가할 수 없는 경우 false를 반환하고 요소가 추가되면 true를 반환하며 특정 예외를 throw하지 않는다.
        //add 메소드는 illegalStateException를 발생시킨다.

        //offer 메서드는 큐가 가득차서 더이상 추가할 수 없는 경우 false를 반환하고 요소가 추가되면 true를 반환하며 특정 예외를 throw하지 않는다.
        //add 메소드는 illegalStateException를 발생시킨다.
        for (s in alphabet) {
            //큐안에 값 넣기
            queue.offer(s)
        }

        CustomLogger.getInstance().info("Queue 값 포함 여부 : " + queue.contains("B"))
        CustomLogger.getInstance().info("Queue 값 포함 다음 출력값 확인하기 : " + queue.peek())
        CustomLogger.getInstance().info("Queue 크기확인: " + queue.size)
        CustomLogger.getInstance().info("Queue 안의 특정값 빼기: " + queue.remove("A"))
        CustomLogger.getInstance().info("Queue 크기확인: " + queue.size)

        //offer 메서드는 큐가 가득차서 더이상 추가할 수 없는 경우 false를 반환하고 요소가 추가되면 true를 반환하며 특정 예외를 throw하지 않는다.
        //add 메소드는 illegalStateException를 발생시킨다.
        (0..queue.size).forEach { _ ->
            CustomLogger.getInstance().info(queue.poll())
        }

        CustomLogger.getInstance().info("Queue가 비었는가? " + queue.isEmpty())
        queue.clear()
        CustomLogger.getInstance().info("Clear이후 Queue가 비었는가? " + queue.isEmpty())
    }
}