package com.example.lib.collection

import org.junit.jupiter.api.Test


/**
 * https://namget.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%8A%A4%ED%8A%B8%EB%A6%BC-%ED%95%A8%EC%88%98-map-flatMap-groupBy-filter-take-drop-first-distinct-zip-joinToString-count-any-none-max-min-average
 */
class CollectionTest {

    private val testList = listOf("A", "B", "C")

    private val newList1 = testList.map { "$it!" }
    private val newList2 = testList.flatMap {
        "$it!".toList()
    }

    @Test
    fun `map 그리고 flatMap 테스트`() {
        println(newList1)
        println(newList2)
    }

    var data = arrayOf(arrayOf("1", "2"), arrayOf("3", "4"))

    @Test
    fun `flatMap Test1`() {
        data.flatMap { x -> x.toList() }.forEach { println(it) }
    }

    @Test
    fun `flatMap Test2`() {
        //하나의 인자에서 여러개의 인자로 매핑이 필요할때 사용
        val numbers = 0..5
        numbers.flatMap { number -> 1..number }.forEach { print("$it") }
    }

    private val animals = listOf("사자", "호랑이", "코끼리")

    @Test
    fun `map Test`() {
        animals.map { animal -> "동물의 이름은? = $animal" }.forEach {
            println(it)
        }
    }

    @Test
    fun `mapIndexed Test`() {
        val numbers = 0..10
        numbers.mapIndexed { index, number -> index * number }.forEach { println(it) }
    }

    @Test
    fun `mapNotNull Test`() {
        animals.mapNotNull { animal -> if (animal.length > 2) animal else null }
            .forEach { println(it) }
    }

    @Test
    fun `groupBy Test`() {
        animals.groupBy { animals -> if (animals.length > 2) "One" else "Two" }
            .forEach { (key, animals) ->
                println("$key animals = $animals")
            }
    }

    @Test
    fun `filter Test`() {
        animals.filter { animal -> animal.length > 2 }.forEach { println(it) }
    }

    @Test
    fun `take Test`() {
        animals.take(1).forEach { println(it) }
        animals.takeLast(1).forEach { println(it) }

        println("takeWhile===========")
        animals.takeWhile { animal -> animal.length > 2 }.forEach { println(it) }
        println("takeLastWhile===========")
        animals.takeLastWhile { animal -> animal.length > 2 }.forEach { println(it) }
    }

    @Test
    fun `drop Test`() {
        animals.drop(1).forEach { println(it) }
        animals.dropLast(1).forEach { println(it) }
        println("takeWhile===========")
        animals.dropWhile { animal -> animal.length == 2 }.forEach { println(it) }
        println("takeLastWhile===========")
        animals.dropLastWhile { animal -> animal.length > 2 }.forEach { println(it) }
    }

    @Test
    fun `first and last Test`() {
        animals.first().forEach { println(it) }
        animals.firstOrNull()?.forEach { println(it) }

        animals.last().forEach { println(it) }
        animals.lastOrNull()?.forEach { println(it) }
    }

    @Test
    fun `distinct Test`() {
        //distinct() 함수는 컬랙션 내에서 중복된 항목을 제외하고 컬랙션을 반환합니다.
        // 중복여부는 equals()로 판단하며 distinctBy()를 사용하여 중복여부를 판단을 설정 할 수 있습니다.
        val animals = listOf("사자", "호랑이", "코끼리", "사자", "호랑이", "코끼리")
        animals.distinct().forEach { println(it) }
        animals.distinctBy { animal -> animal.length }.forEach { println(it) }
    }

    @Test
    fun `zip Test`() {
        val animals = listOf("사자", "호랑이", "코끼리")
        val animals2 = listOf("여우", "늑대", "기린")
        animals.zip(animals2)
            .forEach { pair: Pair<String, String> -> "${pair.first} : ${pair.second}" }

        animals.zip(animals2) { animals: String, animalNames: String -> "$animals : $animalNames" }
            .forEach { println(it) }
    }

    @Test
    fun `joinToString Test`() {
        val animals = listOf("사자", "호랑이", "코끼리")
        println(animals.joinToString())
    }

    @Test
    fun `count Test`() {
        println(animals.count())
        println(animals.count() { animal: String -> animal.length > 2 })
    }

    @Test
    fun `any 그리고 none Test`() {
        //any() 함수는 컬랙션내에 자료가 존재하면 true, 아니면 false 를 반환
        println(animals.any())
        println(animals.any { animal -> animal.length > 2 })
        println(animals.none())
        println(animals.none { animal -> animal.length > 2 })
    }

    @Test
    fun `max min average`() {
        val numbers = listOf(1, 2, 3)
        println(numbers.maxOrNull())
        println(numbers.minOrNull())
        println(numbers.average())
    }

    @Test
    fun `chunked`() {
        val range=0..10
        println(range.chunked(3))
        //return List<List<Int>>
        //[[0,1,2],[3,4,5],[6,7,8],[9,10]]

        println(range.chunked(3){it.sum()})
        //listOf(3,12,21,19)

        println(range.chunked(3){it.average()})
        //listOf(1.0,4.0,7.0,9,5)
    }

    @Test
    fun `컬렉션을 윈도우로 처리하기 windowed`() {
        val range = 0..10
        println(range.windowed(size = 3, step = 3))
        //[[0,1,2],[3,4,5],[6,7,8]]
        //한 리스트에 3개씩, 3만큼 전진
        //마지막 윈도우는 3개가 아님. partialWindows 기본값 false 이기 때문에 출력되지 않음

        println(range.windowed(3, 3) { it.average() })
        //[1.0,4.0,7.0]

        println(range.windowed(3, 1))
        //[[0,1,2],[1,2,3],[2,3,4],[3,4,5],[4,5,6],[6,7,8],[7,8,9],[8,9,10]]
        //한 윈도우에 3개씩, 1만큼 전진
        //[9,10],[10] 은 3개가 들어있지 않고 partialWindows 기본값 false 이기 때문에 출력되지 않음

        println(range.windowed(3, 1) { it.average() })
        //[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]

    }

    @Test
    fun `coerceIn 값이 범위 안에 있으면 해당 값을, 값이 범위 안에 없으면 경계값을 리턴`() {
        val range = 3..8
        println(5.coerceIn(range))  //5
        println(1.coerceIn(range))  //1
        println(9.coerceIn(range))  //8
        println(5.coerceIn(3, 6))   //5
    }
}