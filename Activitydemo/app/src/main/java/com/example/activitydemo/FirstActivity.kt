package com.example.activitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitydemo.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.finishButton.setOnClickListener{
            setResult(RESULT_OK)
            finish()
        }

    }
}