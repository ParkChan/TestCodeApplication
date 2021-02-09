package com.example.testcodeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testcodeapplication.databinding.ActivityMainBinding
import com.example.testcodeapplication.viewmodel.MainViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        mainViewModel.getToken().observe(this,
            Observer<String> {
                lifecycleScope.launch{
                    Logger.d("received token $it")
                }
            })
    }
}