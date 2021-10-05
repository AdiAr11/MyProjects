package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizQuestionsBinding

    private val questionsList: ArrayList<Question> = Constants.getQuestions()
    private var currentQuestionNumber = 0
    private var selectedOption = 0
    private var score = 0
    private var userName: String? = null

    //val activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextQuestion()
        userName = intent.getStringExtra(Constants.USER_NAME)

        binding.progressBar.progress = currentQuestionNumber + 1

        var quesNo = "${currentQuestionNumber + 1}/10"
        binding.quesNumberTextView.text = quesNo

        binding.option1TextView.setOnClickListener(this)
        binding.option2TextView.setOnClickListener(this)
        binding.option3TextView.setOnClickListener(this)
        binding.option4TextView.setOnClickListener(this)

        binding.submitButton.setOnClickListener{

            if(selectedOption == 0){

                //next Question calling
                if(currentQuestionNumber < questionsList.size-1) {
                    currentQuestionNumber++

                    quesNo = "${currentQuestionNumber + 1}/10"
                    binding.quesNumberTextView.text = quesNo
                    binding.submitButton.text = "SUBMIT"
                    nextQuestion()
                    binding.progressBar.progress = currentQuestionNumber + 1
                }
                else{
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(Constants.CORRECT_ANSWERS, score)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                    intent.putExtra(Constants.USER_NAME, userName)
                    startActivity(intent)
                    finish()
                }
            }
            else{
                if (questionsList[currentQuestionNumber].correctAns != selectedOption) {
                    answerView(selectedOption, R.drawable.wrong_option_border)
                }
                else{
                    score++
                }

                answerView(
                    questionsList[currentQuestionNumber].correctAns,
                    R.drawable.correct_option_border
                )
                if (currentQuestionNumber == questionsList.size - 1) {
                    binding.submitButton.text = "FINISH"
                }
                else
                    binding.submitButton.text = "GO TO NEXT QUESTION"
                selectedOption = 0
            }

        }

    }

    private fun answerView(answer: Int, drawableView: Int) {

        when(answer){

            1 -> {
                binding.option1TextView.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                binding.option2TextView.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                binding.option3TextView.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                binding.option4TextView.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun nextQuestion(){

        selectedOption = 0
        makeDefaultOptionView()
        val question: Question = questionsList[currentQuestionNumber]
        binding.questionsTextView.text = question.question
        binding.flagImageView.setImageResource(question.image)
        binding.option1TextView.text = question.optionOne
        binding.option2TextView.text = question.optionTwo
        binding.option3TextView.text = question.optionThree
        binding.option4TextView.text = question.optionFour
    }

    //making all options textViews default background
    private fun makeDefaultOptionView(){

        val options = ArrayList<TextView>()
        options.add(0, binding.option1TextView)
        options.add(1, binding.option2TextView)
        options.add(2, binding.option3TextView)
        options.add(3, binding.option4TextView)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)

        }

    }

    private fun makeSelectedOption(tv: TextView, optionChosenNumber: Int){

        makeDefaultOptionView()
        selectedOption = optionChosenNumber
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.option1TextView -> makeSelectedOption(binding.option1TextView, 1)
            R.id.option2TextView -> makeSelectedOption(binding.option2TextView, 2)
            R.id.option3TextView -> makeSelectedOption(binding.option3TextView, 3)
            R.id.option4TextView -> makeSelectedOption(binding.option4TextView, 4)

        }
    }

}