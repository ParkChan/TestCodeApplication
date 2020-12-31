package com.example.testcodeapplication.ui.rx


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityRxJavaStep3Binding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * Observer
 * Observable
 * 참고 : https://tourspace.tistory.com/279
 * Observable의 생성
 * from 그리고 just
 */
class RxJavaStep3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaStep3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_step3)


        /**
         * from
         */
        val list = listOf(1, 2, 3)
        //val list = listOf(1, 2, 3).toObservable()
        val listOb = Observable.fromIterable(list)

        val call = Callable<Int> { 4 }
        val callOb = Observable.fromCallable(call)

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

        val futureOb = Observable.fromFuture(future)

        val observer: Observer<Int> = object : Observer<Int> {
            override fun onSubscribe(d: Disposable?) {
                println("onSubscribe() - $d")
            }

            override fun onNext(item: Int?) {
                println("onNext() - $item")
            }

            override fun onError(e: Throwable) {
                println("onError() - ${e.message}")
            }

            override fun onComplete() {
                println("onComplete()")
            }
        }
//        listOb.subscribe(observer)
//        callOb.subscribe(observer)
//        futureOb.subscribe(observer)

        /**
         * just
         */
        val list2 = listOf(1, 2, 3)
        val num = 3
        val str = "WOW!"
        val map = mapOf(1 to "one", 2 to "two")

        val justOb = Observable.just(list2, num, str, map)

        val observerJust: Observer<Any> = object : Observer<Any> {
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
            }
        }
        justOb.subscribe(observerJust)

    }
}