package com.example.testcodeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testcodeapplication.databinding.ActivityMainBinding
import com.example.testcodeapplication.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

//https://developer.android.com/reference/android/app/Activity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d(">>> lifecycle is onCreate()")
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        mainViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        mainViewModel.getToken().observe(this,
            Observer<String> {
                lifecycleScope.launch {
                    Timber.d("received token $it")
                }
            })
    }

    override fun onStart() {
        Timber.d(">>> lifecycle is onStart()")
        super.onStart()
    }

    override fun onRestart() {
        Timber.d(">>> lifecycle is onRestart()")
        super.onRestart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Timber.d(">>> lifecycle is onRestoreInstanceState()")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        Timber.d(">>> lifecycle is onResume()")
        super.onResume()
    }

    override fun onPostResume() {
        Timber.d(">>> lifecycle is onPostResume()")
        super.onPostResume()
    }

    override fun onPause() {
        Timber.d(">>> lifecycle is onPause()")
        //투명한 액티비티가 위에 올라오면 onPause 까지
        //다이얼로그 액티비티가 위에 올라오면 onPause 까지
        //불투명한 액티비티가 위에 올라오면 onStop 호출
        super.onPause()
    }

    override fun onStop() {
        Timber.d(">>> lifecycle is onStop()")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.d(">>> lifecycle is onSaveInstanceState()")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Timber.d(">>> lifecycle is onDestroy()")
        super.onDestroy()

    }
}