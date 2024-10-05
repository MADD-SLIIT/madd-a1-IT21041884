package com.example.strokesapplication_02.activity

import android.content.Intent
import android.os.Bundle
import com.example.strokesapplication_02.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener(){
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

    }
}