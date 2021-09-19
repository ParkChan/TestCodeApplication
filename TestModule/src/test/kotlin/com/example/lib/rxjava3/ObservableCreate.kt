package com.example.lib.rxjava3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * Observer
 * Observable
 * 참고 URL
 * https://tourspace.tistory.com/279
 * https://brunch.co.kr/@lonnie/18
 * Observable의 생성
 * from/just/range/empty/interval/timer/구독해지
 *
 * fromIterable(): list 같이 iterable을 지원하는 instance를 Observable 형태로 변경
 * 각 개별 아이템이 하나씩 전달
 *
 * fromCallable(): Callable 객체를 OBservable형태로 변경
 * call() 함수의 return값이 전달
 *
 * fromFuture(): Future 객체를 Observable 형태로 변경
 * get() 함수의 return값이 전달
 *
 * just는 받은 인자를 그대로 전달
 * range 특정 범위만큼 수를 생성하여 전달
 * empty 아무값을 전달하지는 않지만 onComplete()를 호출
 * interval 특정 시간 간격으로 0부터 숫자를 증가시키면서 반환
 * timer 주어진 시간에 한번만 값을 전달
 */

class ObservableCreate {

    @Test
    fun createTest() {
        println("create test start===============")
        create()
    }

    @Test
    fun fromIterableTest() {
        println("fromIterable test start===============")
        fromIterable()
    }

    @Test
    fun fromCallAbleTest() {
        println("fromCallAble test start===============")
        fromCallAble()
    }

    @Test
    fun fromFutureTest() {
        println("fromFuture test start===============")
        fromFuture()
    }

    @Test
    fun justTest() {
        println("just test start===============")
        just()
    }

    @Test
    fun rangeTest() {
        println("range test start===============")
        range()
    }

    @Test
    fun emptyTest() {
        println("empty test start===============")
        empty()
    }

    @Test
    fun intervalTest(){
        println("interval test start===============")
        interval()
        Thread.sleep(3000)
    }

    @Test
    fun timerTest() {
        println("timer test start===============")
        timer()
        Thread.sleep(3000)
    }

    /**
     * Create
     */
    private fun create() {
        val simpleObservable1: Observable<String> = Observable.create {
            println("simpleObservable1 Thread Name " + Thread.currentThread().name)
            it.onNext("A")
            it.onNext("B")
            it.onComplete()
        }

        val simpleObservable2: Observable<String> = Observable.create {
            println("simpleObservable2 Thread Name " + Thread.currentThread().name)
            it.onNext("C")
            it.onNext("D")
            it.onError(Exception("Opps!! Exception"))
        }

        simpleObservable1.subscribe(observer)
        simpleObservable2.subscribe(observer)
    }

    private fun fromIterable() {
        val list = arrayListOf(1, 2, 3)
        //val list = listOf(1, 2, 3).toObservable()
        val listOb: Observable<Int> = Observable.fromIterable(list)

        //listOb.subscribe(observer)
        //observer의 instance를 등록해도 되지만 필요한 callback만 따로 등록해서 사용할 수 있음
        listOb.subscribe(
            { item -> println("onNext - $item") },
            { err -> println("err - $err") },
            {
                println("complete()")
                println("End============================")
            }
        )
    }

    private fun fromCallAble() {
        val call = Callable<Int> { 4 }
        val callOb: Observable<Int> = Observable.fromCallable(call)
        callOb.subscribe(observer)
    }

    private fun fromFuture() {
        val future = object : Future<Int> {
            override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
                return false
            }

            override fun isCancelled(): Boolean {
                return false
            }

            override fun isDone(): Boolean {
                return true
            }

            override fun get(): Int {
                return 5
            }

            override fun get(timeout: Long, unit: TimeUnit): Int {
                return 6
            }
        }
        val futureOb: Observable<Int> = Observable.fromFuture(future)
        futureOb.subscribe(observer)
    }

    private fun just() {
        val list2 = listOf(1, 2, 3)
        val num = 3
        val str = "WOW!"
        val map = mapOf(1 to "one", 2 to "two")

        val justOb = Observable.just(list2, num, str, map)
        justOb.subscribe(observer)
    }

    private fun range() {
        Observable.range(1, 3).subscribe(observer)
    }

    private fun empty() {
        Observable.empty<String>().subscribe(observer)
    }

    private fun interval() {
        //특정 시간 간격을 주기로 0부터 증가하는 정수 값을 발행한다.
        val observableInterval = Observable.interval(1, TimeUnit.SECONDS)
        //observableInterval.take(3).subscribe(observer)

        val disposable: Disposable = observableInterval.subscribe(
            { item -> println("Test onNext - $item") },
            { err -> println("Test err - $err") },
            {
                println("Test complete()")
                println("End============================")
            }
        )

//                val disposableObserver: DisposableObserver<Any> = object : DisposableObserver<Any>() {
//                    override fun onNext(item: Any?) {
//                        println("onNext() - $item")
//                    }
//
//                    override fun onError(e: Throwable) {
//                        println("onError() - ${e.message}")
//                    }
//
//                    override fun onComplete() {
//                        println("onComplete()")
//                    }
//                }
//                val disposable: Disposable = observableInterval.subscribeWith(disposableObserver)
    }

    private fun timer() {
        //특정 시간 이후에 숫자 0을 발행한다. 스케줄러로 computation을 사용하고 변경 가능
        //주어진 시간에 한번만 값을 전달
        val source: Observable<Long> = Observable.timer(3, TimeUnit.SECONDS)
        source.subscribe(observer)
    }

    private val observer: Observer<Any> = object : Observer<Any> {
        override fun onSubscribe(d: Disposable?) {
            println("onSubscribe() - $d")
        }

        override fun onNext(item: Any?) {
            println("onNext() - $item")
        }

        override fun onError(e: Throwable) {
            println("onError() - ${e.message}")
        }

        override fun onComplete() {
            println("onComplete()")
            println("End============================")
        }
    }
}

