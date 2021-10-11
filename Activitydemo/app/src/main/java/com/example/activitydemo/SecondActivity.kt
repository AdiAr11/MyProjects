package com.example.activitydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitydemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SubmitButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MainActivity.NAME_KEY, binding.NameEditText.text.toString())
            intent.putExtra(MainActivity.EMAIL_KEY, binding.emailEditText.text.toString())
            setResult(RESULT_OK, intent)
            finish()

        }

    }
}