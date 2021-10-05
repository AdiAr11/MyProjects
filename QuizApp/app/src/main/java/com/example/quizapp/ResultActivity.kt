package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding:ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctAns = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val totalQues = intent.getIntExtra(Constants.TOTAL_QUESTIONS,10)
        val score =  "your score is $correctAns out of $totalQues"

        binding.scoreTextView.text = score
        binding.userNameTextView.text = intent.getStringExtra(Constants.USER_NAME)

        binding.finishButton.setOnClickListener {
            finish()
        }

    }
}