package com.example.activitydemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.activitydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    companion object{
        const val NAME_KEY = "name"
        const val EMAIL_KEY = "email"
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if (result.resultCode == RESULT_OK){

            val data:Intent? = result.data
            if(data == null)
                binding.firstActivityResultTextView.text = "First Activity Result Success"

            else if (data != null) {
                val name = data.getStringExtra(NAME_KEY)    //Important
                val email = data.getStringExtra(EMAIL_KEY)
                val text = "name = $name : email = $email"
                binding.secondActivityResultTextView.text = text
            }
        }
        else if(result.resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Result Cancelled", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.FirstActivityButton.setOnClickListener{

            val intent = Intent(this, FirstActivity::class.java)
            resultLauncher.launch(intent)
        }

        binding.SecondActivityButton.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            resultLauncher.launch(intent)
        }

    }
}