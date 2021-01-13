package com.example.lib.kotlin

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

/**
 * 일반적으로 Observable 을 테스트시 Blocking을 사용
 */
class Rxjava3ConcatMergeZip {}

fun main() {
    println("===========")
    val test1 = Observable.just("1", "2", "3").delay(2, TimeUnit.SECONDS)
    val test2 = Observable.just("apple", "banana", "car")
    Observable.concat(test1, test2)
        .blockingSubscribe(
            {
                println("concat observable next $it")
            }, {
                println("concat observable error $it")
            }, {
                println("concat observable complete")
            }
        )
}