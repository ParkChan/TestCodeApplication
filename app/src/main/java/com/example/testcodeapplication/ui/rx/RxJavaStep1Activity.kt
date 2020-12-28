package com.example.testcodeapplication.ui.rx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityRxJavaStep1Binding
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * Rxjava의 동작 기본 형태
 */
class RxJavaStep1Activity : AppCompatActivity() {


    private lateinit var binding: ActivityRxJavaStep1Binding

    private val animals = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_step1)

        animals.add("Tiger")
        animals.add("Lion")
        animals.add("Elephant")

        //subscribeOn() thread you need the work to perform on : observable의 작업을 시작하는 쓰레드를 선택
        //observeOn() thread you need to handle the result on : 이후에 나오는 오퍼레이터, subscribe의 스케쥴러를 변경 할 수 있습니다.
        Observable.just(animals)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : Observer<ArrayList<String>> {
                //Observer가 Observable을 구독 할 때 메서드가 호출됩니다.
                override fun onSubscribe(d: Disposable) {
                    Logger.d("Observable onSubscribe")
                }

                //Observable이 데이터 방출을 시작할 때 호출됩니다.
                override fun onNext(t: ArrayList<String>) {
                    Logger.d("Observable onNext")
                }

                //Observable이 모든 항목의 방출을 완료하면 onComplete ()가 호출됩니다.
                override fun onComplete() {
                    Logger.d("Observable onComplete")
                }

                //오류 발생시 onError() 메서드가 호출됩니다.
                override fun onError(e: Throwable) {
                    Logger.d("Observable onError")
                }
            })

    }


    override fun onDestroy() {
        super.onDestroy()
    }
}