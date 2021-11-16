package com.example.lib.basic

import org.junit.jupiter.api.Test

/**
 * equals(Object)가 두 객체를 같다고 판단했으면, 두 객체의 hashCode 값은 항상 같다.
 * 하지만 equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객채의 hashCode 값은 같을 수 있다. (해시 충돌)
 */
class HashCodeTest {

    @Test
    fun `equals만 재정의하면`() {
        //HashMap을 사용할 경우에 hashcode를 재정의 하지 않았을시
        //Key 값으로 해당 객체를 사용할 경우 문제가 발생한다.
        val personHashMap = HashMap<Person, Person>()
        personHashMap[Person(1, "찬1")] = Person(1, "찬1")
        personHashMap[Person(2, "찬2")] = Person(2, "찬2")
        personHashMap[Person(3, "찬3")] = Person(3, "찬3")

        //Person 객체에 대한 해시값을 personHashMap에서 찾을 수 없기 때문
        println(personHashMap[Person(1, "찬1")]?.age)
        println(personHashMap[Person(1, "찬1")]?.name)
        //equals를 재정의 하면 hashcode도 재정의 해줘야 한다.
    }

    class Person(val age: Int, val name: String) {
        override fun equals(other: Any?): Boolean {
            if (other !is Person) {
                return false
            }
            return age == other.age && name == other.name
        }
    }

    @Test
    fun `hashCode만 재정의하면`() {
        //
        //Key값으로 해당 객체를 사용할 경우 문제가 발생한다.
        val personHashMap = HashMap<Person2, Person2>()
        personHashMap[Person2(1, "찬1")] = Person2(1, "찬1")
        personHashMap[Person2(2, "찬2")] = Person2(2, "찬2")
        personHashMap[Person2(3, "찬3")] = Person2(3, "찬3")

        //Person 객체에 대한 해시값은 찾았지만 같은 객체인지 모르기 때문에 값을 가져오지 못한다.
        println(personHashMap[Person2(1, "찬1")]?.age)
        println(personHashMap[Person2(1, "찬1")]?.name)
    }

    class Person2(val age: Int, val name: String) {
        override fun hashCode(): Int {
            return 0    //해시코드가 같으면 해시충돌로 인하여 LinkedList 형태로 저장된다.(성능저하) 8개이후 부터는 트리구조(색인성능)
        }
    }

    @Test
    fun `equals 와 hashCode 를 모두 재정의`() {
        val personHashMap = HashMap<Person3, Person3>()
        personHashMap[Person3(1, "찬1")] = Person3(1, "찬1")
        personHashMap[Person3(2, "찬2")] = Person3(2, "찬2")
        personHashMap[Person3(3, "찬3")] = Person3(3, "찬3")

        //Person 객체에 대한 해시값은 찾았지만 같은 객체인지 모르기 때문에 값을 가져오지 못한다.
        println(personHashMap[Person3(1, "찬1")]?.age)
        println(personHashMap[Person3(1, "찬1")]?.name)
    }

    class Person3(val age: Int, val name: String) {
        override fun equals(other: Any?): Boolean {
            if (other !is Person3) {
                return false
            }
            return age == other.age && name == other.name
        }

        override fun hashCode(): Int {
            val c = 31
            var hashcode = Integer.hashCode(age)
            hashcode = c * hashcode + name.hashCode()
            return hashcode
        }
    }
}