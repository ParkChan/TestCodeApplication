package com.example.lib.rxjava3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Observer
 * Observable
 * 참고 URL
 * 공식문서 http://reactivex.io/documentation/operators/merge.html
 * https://namget.tistory.com/entry/RxKotlinRxJava-merge-zip-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0
 *
 * concat , merge, zip 예제
 *
 * concat은 두개의 Observable을 합쳐 첫번째 Observable을 발행 한 뒤 두번째 Observable을 발행
 * merge는 두개의 Observable를 merge시켜 하나로 결합해 전송
 * zip은 여러개의 Observable을 합쳐서 전송. 특정 item끼리 합쳐서 두개의 발행을 합쳐서 내려줌
 * 가장 최근에 zip 되지 않는 데이터들을 zip
 * combineLatest
 *
 * 기타 사항
 * 일반적으로 Observable 을 테스트시 Blocking을 사용(반드시 테스트 코드에서만 사용)
 */

class ConcatMergeZip {

    @Test
    fun concatTest() {
        val test = ConcatMergeZip()
        test.concat()
    }

    @Test
    fun mergeTest() {
        val test = ConcatMergeZip()
        test.merge()
    }

    @Test
    fun zipTest() {
        val test = ConcatMergeZip()
        test.zip()
    }

    private fun concat() {
        println("Observable.concat TestStart===========")
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

    private fun merge() {
        println("Observable.concat mergeTest===========")
        //두개의 다른 timestamp를 합쳐 하나의 timeStamp에서 발행하는것처럼 변경하여줌
        //하지만 둘중에 하나가 onError를 타게되면 그 즉시 멈추게 됨
        //이것을 방지하기위해 mergeDelayError 사용 > onError난 부분을 빼고 발행후 onError 발행
        val test1 = Observable.just("1", "2", "3").delay(2, TimeUnit.SECONDS)
        val test2 = Observable.just("apple", "banana", "car")
        //val disposable: Disposable = Observable.mergeDelayError(test1, test2)
        Observable.merge(test1, test2)
                .blockingSubscribe(
                        {
                            println("merge observable next $it")
                        }, {
                    println("merge observable error $it")
                }, {
                    println("merge observable complete")
                }
                )
    }

    private fun zip() {
        println("Observable.concat ZipTest===========")
        val test2 = Observable.just("apple", "banana", "car")
        val test3 = Observable.interval(2, TimeUnit.SECONDS)

        Observable.zip(
                test3,
                test2,
                BiFunction { t1: Long, t2: String -> t1.toString() + t2 })
                .blockingSubscribe(
                        {
                            println("zip observable next $it")
                        }, {
                    println("zip observable error $it")
                }, {
                    println("zip observable complete")
                }
                )
    }
}