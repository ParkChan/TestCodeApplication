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
    }

    /**
     * RxJava와 RxAndroid에서 제공하는 스케쥴러는 다음과 같습니다.
     * Schedulers.computation() - 이벤트 룹에서 간단한 연산이나 콜백 처리를 위해 사용됩니다.
     * RxComputationThreadPool라는 별도의 스레드 풀에서 돌아갑니다. 최대 갯수 n개의 스레드 풀이 순환하면서 실행됩니다.
     * Schedulers.immediate() - 현재 스레드에서 즉시 수행합니다.
     * observeOn()이 여러번 쓰였을 경우 immediate()를 선언한 바로 윗쪽의 스레드를 따라갑니다.
     * Schedulers.from(executor) - 특정 executor를 스케쥴러로 사용합니다.
     * Schedulers.io() - 동기 I/O를 별도로 처리시켜 비동기 효율을 얻기 위한 스케줄러입니다.
     * 자체적인 스레드 풀 CachedThreadPool을 사용합니다. API 호출 등 네트워크를 사용한 호출 시 사용됩니다.
     * Schedulers.newThread() - 새로운 스레드를 만드는 스케쥴러입니다.
     * Schedulers.trampoline() - 큐에 있는 일이 끝나면 이어서 현재 스레드에서 수행하는 스케쥴러.
     * AndroidSchedulers.mainThread() - 안드로이드의 UI 스레드에서 동작합니다.
     * HandlerScheduler.from(handler) - 특정 핸들러 handler에 의존하여 동작합니다.
     * 출처: https://tiii.tistory.com/18 [안드로이드 개발 팁 블로그]
     */

    override fun onDestroy() {
        super.onDestroy()
    }
}