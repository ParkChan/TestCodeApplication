package com.example.testcodeapplication.ui.rx


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityRxJavaStep4Binding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.toObservable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


/**
 * Observer
 * Observable
 * 참고 URL
 * https://tourspace.tistory.com/279
 * Hot & Cold Observable
 */
class RxJavaStep4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaStep4Binding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_step4)
        binding.lifecycleOwner = this




        binding.run {
            btnHotObservable.setOnClickListener {
                val connectableObservable = (1..10).toObservable().publish()

                //1번 구독자 등록
                connectableObservable.subscribe { println("first subscriber: $it") }
                println("Add first subscriber")

                //2번 구독자 등록
                connectableObservable.map { "second subscriber: $it" }
                    .subscribe { println(it) }
                println("Add second subscriber")

                //observerable connect()
                compositeDisposable.add(connectableObservable.connect())

                //3번 구독자 등록
                //1번과 2번 구독자를 등록후 Observable의 connect()를 호출하면 그때서야 데이터가 배출됨
                //또한 배출이 완료된 이후에 등록된 3번은 데이터를 하나도 전달받지 못합니다.
                connectableObservable.subscribe { println("Subscription 3: $it") }
            }

            btnHotObservableTest.setOnClickListener {
                val connectableObservable = Observable.interval(1, TimeUnit.SECONDS).publish()

                //1번 구독자 등록
                connectableObservable.subscribe { println("first subscriber: $it") }
                println("Add first subscriber")

                //2번 구독자 등록
                connectableObservable.map { "second subscriber: $it" }
                    .subscribe { println(it) }
                println("Add second subscriber")

                compositeDisposable.add(connectableObservable.connect())

                runBlocking {
                    delay(3000)
                }
                //3번 구독자 등록
                //connect() 이후에 300ms을 대기하므로 1번과 2번 구독자는 각각 세개씩 배출된 데이터를 받습니다.
                //그 이후 3번 구독자가 등록되면 3번은 등록 이후 데이터 부터 전달받습니다.
                connectableObservable.subscribe { println("Subscription 3: $it") }

                runBlocking {
                    delay(3000)
                }

            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onDestroy()
    }
}