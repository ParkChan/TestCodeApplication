package com.example.testcodeapplication.ui.rx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityRxJavaStep2Binding
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Observer
 * Observable
 * 참고 : https://tourspace.tistory.com/279
 * Observable의 생성
 * create
 */
class RxJavaStep2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaStep2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_step2)

        val observer: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
//                Logger.d("observer onSubscribe currentThread is ${Thread.currentThread().name} ")
                Logger.d("observer onSubscribe")
            }

            override fun onNext(t: String) {
//                Logger.d("observer onNext currentThread is ${Thread.currentThread().name} ")
                Logger.d("observer onNext $t")
            }

            override fun onError(e: Throwable) {
//                Logger.d("observer onError currentThread is ${Thread.currentThread().name} ")
                Logger.d("observer onError" + e.message)
            }

            override fun onComplete() {
//                Logger.d("observer onComplete currentThread is ${Thread.currentThread().name} ")
                Logger.d("observer onComplete")
            }
        }

        val simpleObservable1: Observable<String> = Observable.create {
            Logger.v("simpleObservable1 Thread Name " + Thread.currentThread().name)
            it.onNext("A")
            it.onNext("B")
            it.onComplete()
        }

        val simpleObservable2: Observable<String> = Observable.create {
            Logger.v("simpleObservable2 Thread Name " + Thread.currentThread().name)
            it.onNext("C")
            it.onNext("D")
            it.onError(Exception("Opps!! Exception"))
        }

        simpleObservable1.subscribe(observer)
        simpleObservable2.subscribe(observer)

        //Observable.concat(simpleObservable1, simpleObservable2).subscribe(observer)

    }
}