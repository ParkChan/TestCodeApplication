package com.example.testcodeapplication.ui.rx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testcodeapplication.R
import com.example.testcodeapplication.databinding.ActivityRxJavaMainBinding


class RxJavaMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_main)
        binding.btnStep1.setOnClickListener {
            val intent = Intent(this, RxJavaStep1Activity::class.java)
            startActivity(intent)
        }
        binding.btnStep2.setOnClickListener {
            val intent = Intent(this, RxJavaStep2Activity::class.java)
            startActivity(intent)
        }
        binding.btnStep3.setOnClickListener {
            val intent = Intent(this, RxJavaStep3Activity::class.java)
            startActivity(intent)
        }
    }

}