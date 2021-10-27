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


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d(">>> lifecycle is onCreate()")
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
        super.onStart()
        Timber.d(">>> lifecycle is onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d(">>> lifecycle is onRestart()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d(">>> lifecycle is onRestoreInstanceState()")
    }

    override fun onResume() {
        super.onResume()
        Timber.d(">>> lifecycle is onResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.d(">>> lifecycle is onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.d(">>> lifecycle is onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d(">>> lifecycle is onSaveInstanceState()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d(">>> lifecycle is onDestroy()")
    }
}