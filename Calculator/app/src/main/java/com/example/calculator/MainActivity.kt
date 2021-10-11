package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

private lateinit var binding:ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private var isLastNumeric = false
    private var isDecimalUsed = false
    private lateinit var operator: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clearButton.setOnClickListener{
            binding.inputScreenTextView.text = ""
            isLastNumeric = false
            isDecimalUsed = false
        }

        binding.decimalButton.setOnClickListener{

            if (!isDecimalUsed && isLastNumeric) {
                binding.inputScreenTextView.append(".")
                isDecimalUsed = true
                isLastNumeric = false
            }
        }

        binding.calculateButton.setOnClickListener{
            if(isLastNumeric) {
                try {
                    var screenValue = binding.inputScreenTextView.text.toString()
                    var prefix = ""

                    if (screenValue.startsWith("-")){
                        prefix = "-"
                        screenValue = screenValue.substring(1)
                    }

                    if (screenValue.contains("-")) {
                        val parts = screenValue.split("-")
                        var one = parts[0]
                        val two = parts[1]
                        if (prefix == "-"){
                            one = prefix + one
                        }
                        binding.inputScreenTextView.text =
                            removeDotZero((one.toDouble() - two.toDouble()).toString())
                    }

                    else if(screenValue.contains("+")){
                        val parts = screenValue.split("+")
                        var one = parts[0]
                        val two = parts[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        binding.inputScreenTextView.text =
                            removeDotZero((one.toDouble() + two.toDouble()).toString())
                    }

                    else if(screenValue.contains("x")){
                        val parts = screenValue.split("x")
                        var one = parts[0]
                        val two = parts[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        binding.inputScreenTextView.text =
                            removeDotZero((one.toDouble() * two.toDouble()).toString())
                    }

                    else if (screenValue.contains("/")){

                        val parts = screenValue.split("/")
                        var one = parts[0]
                        val two = parts[1]
                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }
                        binding.inputScreenTextView.text =
                            removeDotZero((one.toDouble() / two.toDouble()).toString())
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }
            else{
                Toast.makeText(this,"Incorrect calculation", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun onDigit(view: View){

        binding.inputScreenTextView.append((view as Button).text)
        isLastNumeric = true
    }

    fun onOperatorClick(view: View){
        if (isLastNumeric && !isOperatorAdded(binding.inputScreenTextView.text.toString())) {
            operator = (view as Button).text.toString()
            binding.inputScreenTextView.append(view.text)
            isLastNumeric = false
        }

    }

    private fun isOperatorAdded(values: String): Boolean{
        return if(values.startsWith("-")){
            false
        }else{
            values.contains("/") || values.contains("x") || values.contains("+") || values.contains("-")
        }
    }

    private fun removeDotZero(result: String): String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

}