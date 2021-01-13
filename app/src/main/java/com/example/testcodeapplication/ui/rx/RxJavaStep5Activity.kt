package com.example.testcodeapplication.ui.rx


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityRxJavaStep5Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
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
 */
class RxJavaStep5Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaStep5Binding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_step5)
        binding.lifecycleOwner = this

        binding.run {
            btnConcat.setOnClickListener {
                val test1 = Observable.just("1", "2", "3").delay(2, TimeUnit.SECONDS)
                val test2 = Observable.just("apple", "banana", "car")
                val disposable: Disposable = Observable.concat(test1, test2).subscribe(
                    {
                        println("concat observable next $it")
                    }, {
                        println("concat observable error $it")
                    }, {
                        println("concat observable complete")
                    }
                )
                compositeDisposable.add(disposable)
            }
            btnMerge.setOnClickListener {
                //두개의 다른 timestamp를 합쳐 하나의 timeStamp에서 발행하는것처럼 변경하여줌
                //하지만 둘중에 하나가 onError를 타게되면 그 즉시 멈추게 됨
                //이것을 방지하기위해 mergeDelayError 사용 > onError난 부분을 빼고 발행후 onError 발행
                val test1 = Observable.just("1", "2", "3").delay(2, TimeUnit.SECONDS)
                val test2 = Observable.just("apple", "banana", "car")
                //val disposable: Disposable = Observable.mergeDelayError(test1, test2)
                val disposable: Disposable = Observable.merge(test1, test2)
                    .subscribe(
                        {
                            println("merge observable next $it")
                        }, {
                            println("merge observable error $it")
                        }, {
                            println("merge observable complete")
                        }
                    )
                compositeDisposable.add(disposable)
            }
            btnZip.setOnClickListener {
                val test2 = Observable.just("apple", "banana", "car")
                val test3 = Observable.interval(2, TimeUnit.SECONDS)

                val disposable = Observable.zip(
                    test3,
                    test2,
                    BiFunction { t1: Long, t2: String -> t1.toString() + t2 })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            println("zip observable next $it")
                        }, {
                            println("zip observable error $it")
                        }, {
                            println("zip observable complete")
                        }
                    )
                compositeDisposable.add(disposable)
            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onDestroy()
    }
}