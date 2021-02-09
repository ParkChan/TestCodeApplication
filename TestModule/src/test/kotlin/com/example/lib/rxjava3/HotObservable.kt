package com.example.lib.rxjava3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Observer
 * Observable
 * 참고 URL
 * https://tourspace.tistory.com/279
 * Hot & Cold Observable
 */

class HotObservable {

    @Test
    fun hotStreamTest1(){
        val test = HotObservable()
        test.hotStream1()
    }

    @Test
    fun hotStreamTest2(){
        val test = HotObservable()
        test.hotStream2()
    }

    private fun hotStream1() {
        println("hotStreamTest1 Start===========")
        val connectAbleObservable = (1..10).toObservable().publish()

        //1번 구독자 등록
        connectAbleObservable.subscribe { println("first subscriber: $it") }
        println("Add first subscriber")

        //2번 구독자 등록
        connectAbleObservable.map { "second subscriber: $it" }
            .subscribe { println(it) }
        println("Add second subscriber")

        connectAbleObservable.connect()

        //3번 구독자 등록
        //1번과 2번 구독자를 등록후 Observable의 connect()를 호출하면 그때서야 데이터가 배출됨
        //또한 배출이 완료된 이후에 등록된 3번은 데이터를 하나도 전달받지 못합니다.
        connectAbleObservable.subscribe { println("Subscription 3: $it") }
    }
    private fun hotStream2() {
        println("hotStreamTest2 Start===========")
        val connectAbleObservable = Observable.interval(1, TimeUnit.SECONDS).publish()

        //1번 구독자 등록
        connectAbleObservable.subscribe { println("first subscriber: $it") }
        println("Add first subscriber")

        //2번 구독자 등록
        connectAbleObservable.map { "second subscriber: $it" }
            .subscribe { println(it) }
        println("Add second subscriber")

        connectAbleObservable.connect()

        Thread.sleep(3000)

        //3번 구독자 등록
        //connect() 이후에 300ms을 대기하므로 1번과 2번 구독자는 각각 세개씩 배출된 데이터를 받습니다.
        //그 이후 3번 구독자가 등록되면 3번은 등록 이후 데이터 부터 전달받습니다.
        connectAbleObservable.subscribe { println("Subscription 3: $it") }

        Thread.sleep(3000)
    }
}