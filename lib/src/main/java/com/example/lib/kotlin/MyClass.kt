package com.example.lib.kotlin

import java.util.*

fun main(args: Array<String>) {

     val progresses = intArrayOf(93, 30, 55)
     val speeds = intArrayOf(1, 30, 5)

     println("solution is >> " + Arrays.toString(solution(progresses, speeds)))
}

fun solution(progresses: IntArray, speeds: IntArray): IntArray? {
     val queue: Queue<Int> = ArrayDeque()
     for (i in progresses.indices) {
          queue.add(
               if ((100 - progresses[i]) % speeds[i] == 0) (100 - progresses[i]) / speeds[i]
               else (100 - progresses[i]) / speeds[i] + 1
          )
     }
     println("queue is >> $queue")
     val result: MutableList<Int> = ArrayList()
     var compareNum = queue.poll()
     var updateCnt = 1
     while (!queue.isEmpty()) {
          val num = queue.poll()
          if (compareNum >= num) {
               updateCnt++
          } else {
               result.add(updateCnt)
               updateCnt = 1
               compareNum = num
          }
     }
     result.add(updateCnt)
     val answer = IntArray(result.size)
     for (i in answer.indices) {
          answer[i] = result[i]
     }
     return answer
}

<<<<<<< HEAD
fun main() {
     print("hello world")
}
=======
>>>>>>> master
