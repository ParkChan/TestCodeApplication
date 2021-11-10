package com.example.lib.collection.basic.list.linkedlist

import com.example.lib.java.util.CustomLogger
import org.junit.jupiter.api.Test
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

class LinkedBlockingDequeTest {

    @Test
    fun `LinkedBlockingDeque Test`() {
        val deque: BlockingDeque<String> = LinkedBlockingDeque()

        deque.addFirst("1")
        deque.addLast("2")

        try {
            val two = deque.takeLast()
            val one = deque.takeFirst()
            CustomLogger.getInstance().info("two >>> $two")
            CustomLogger.getInstance().info("one >>> $one")
            CustomLogger.getInstance().info("deque size >>> " + deque.size)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}