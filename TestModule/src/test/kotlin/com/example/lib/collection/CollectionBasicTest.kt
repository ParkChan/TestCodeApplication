package com.example.lib.collection

import org.junit.jupiter.api.Test

class CollectionBasicTest {

    private data class Person(val name: String, val age: Int)

    private val canBeInClub27 = { p: Person -> p.age <= 27 }

    @Test
    fun `Map Test`() {
        val people = listOf(
            Person("Alice", 29),
            Person("Bob", 31),
            Person("Charles", 31)
        )
        println(people.map { it.name })
    }

    @Test
    fun `Find Test`() {
        val people = listOf(
            Person("Alice", 27),
            Person("Bob", 31)
        )
        println(people.find(canBeInClub27))
    }

    @Test
    fun `GroupBy Test`() {
        val people = listOf(
            Person("Alice", 31),
            Person("Bob", 29),
            Person("Carol", 31)
        )
        println(people.groupBy { it.age })
    }

    @Test
    fun `collection의 element를 key로 사용하려면 associateWith()를 사용`(){
        val numbers = listOf("one", "two", "three", "four")
        println(numbers.associateWith { it.length })
        // output:
        // {one=3, two=3, three=5, four=4}
    }

    @Test
    fun `collection의 element를 value로 사용하려면 associateBy()를 사용`(){
        val numbers = listOf("one", "two", "three", "four")
        println(numbers.associateBy{ it.length })
        // output:
        // {one=3, two=3, three=5, four=4}
    }

    @Test
    fun `flatMap Test`() {
        val people = listOf(
            "abc",
            "edf",
            "ghi",
        )
        println(people.flatMap { it.toList() })
    }

    @Test
    fun `flatten Test`() {
        val people = listOf(
            listOf("a", "b", "c"),
            listOf("가", "나", "다")
        )
        println(people.flatten())
    }

    @Test
    fun `list add Test`() {
        val abc = listOf("a", "b", "c")
        val def = listOf("가", "나", "다")
        println(abc + def)
        println(abc.plus(def))
    }

    @Test
    fun `withIndex Test`() {
        val chars = listOf("a", "b", "c", "가", "나", "다")
        for ((index, char) in chars.withIndex()) {
            println("index : ${index.toString()}  value: $char")
        }
    }

    @Test
    fun `중복제거`() {
        val data = arrayOf(1, 3, 6, 4, 1, 2)
        println(data.distinct())

    }

    @Test
    fun `sort 메소드는 해당 Collection 의 원소 위치가 변경됩니다`() {
        val data = arrayOf(1, 3, 6, 4, 1, 2)
        data.sort()
        print("sort Data ${data.toList()}")
    }

    @Test
    fun `sorted 메소드를 사용하면 기존 Collection 은 변하지 않습니다`() {
        val data = arrayOf(1, 3, 6, 4, 1, 2)
        print("sortedData ${data.sorted()}")
        println()
        print("originData ${data.toList()}")
        println()
    }

    @Test
    fun `reduce 와 fold 기본 예제`(){
        val numbers = (1..10).toList()

        // total의 초기값은 numbers[1]로 시작한다.
        val sumUsingReduce = numbers.reduce {total, num ->
            println("$total $num")
            total + num
        }
        println(sumUsingReduce) // 55

        // total의 초기값은 100으로 시작한다.
        val sumUsingFold = numbers.fold(100) { total, num ->
            total + num
        }
        println(sumUsingFold) // 155
    }

    @Test
    fun `reduce 와 fold 예외 상황`(){
        val numbers = (1..5).toList()

        // total의 초기값은 numbers[1]로 시작한다.
        val sumUsingReduce = numbers.reduce {total, num ->
            total + num * 2
        }
        println(sumUsingReduce) // 29(?)
        //reduce는 다음 코드와 같이 초기값을 첫번째 요소로 정해놓고 다음 요소부터 연산을 시작한다

        // total의 초기값은 0으로 시작한다.
        val sumUsingFold = numbers.fold(0) { total, num ->
            total + num * 2
        }
        println(sumUsingFold) // 30
    }

    @Test
    fun `reduce 와 fold Empty 리스트에 따른 반환값`(){

        // total의 초기값은 numbers[1]로 시작한다.
        val sumUsingReduce = intArrayOf().reduce {total, num ->
            total + num
        }
        println(sumUsingReduce) // java.lang.UnsupportedOperationException: Empty array can't be reduced.

        // total의 초기값은 0으로 시작한다.
        val sumUsingFold = intArrayOf().fold(11) { total, num ->
            total + num
        }
        println(sumUsingFold)
    }
}