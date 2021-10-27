package com.example.lib.basic

import org.junit.jupiter.api.Test

/**
 * Object Class에 대해서 알아봅니다.
 */
class ObjectTest {


    class Student(val name: String, val age: Int) : Cloneable {
        public override fun clone(): Any {
            return super.clone()
        }
    }
    //Clone
    @Test
    fun `Test Object의 clone을 사용하려면 Cloneable 구현을 반드시 해야합니다`() {
        val student1 = Student("Chan", 37)
        val student2 = student1.clone() as Student
        println(student1.name)
        println(student2.name)
    }

    data class StudentDataClass(val name: String, val age: Int)
    @Test
    fun `Data Class를 사용하면 copy를 자동으로 만들어줍니다`() {
        val student1 = StudentDataClass("Chan", 37)
        val student2 = student1.copy()
        println(student1.name)
        println(student2.name)
    }

    @Test
    fun `Object 클래스 설명(자바 기준)`(){
       /*
        protected Object clone()
        해당 객체의 복제본을 생성하여 반환함.

        boolean equals(Object obj)
        해당 객체와 전달받은 객체가 같은지 여부를 반환함.

        protected void finalize()
        해당 객체를 더는 아무도 참조하지 않아 가비지 컬렉터가 객체의 리소스를 정리하기 위해 호출함.

        Class<T> getClass()
        해당 객체의 클래스 타입을 반환함.

        int hashCode()
        해당 객체의 해시 코드값을 반환함.

        void notify()
        해당 객체의 대기(wait)하고 있는 하나의 스레드를 다시 실행할 때 호출함.

        void notifyAll()
        해당 객체의 대기(wait)하고 있는 모든 스레드를 다시 실행할 때 호출함.

        String toString()
        해당 객체의 정보를 문자열로 반환함.

        void wait()
        해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행할 때까지
        현재 스레드를 일시적으로 대기(wait)시킬 때 호출함.

        void wait(long timeout)
        해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행하거나
        전달받은 시간이 지날 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함.

        void wait(long timeout, int nanos)
        해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행하거나
        전달받은 시간이 지나거나 다른 스레드가 현재 스레드를 인터럽트(interrupt) 할 때까지
        현재 스레드를 일시적으로 대기(wait)시킬 때 호출함.
        */
    }
}