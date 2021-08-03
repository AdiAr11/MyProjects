package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.braintrainer.databinding.ActivityMainBinding;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CountDownTimer timer;

    int locationOfCorrectAns;
    int quesCounter = 0;
    int ansGuessedCorrect = 0;

    Random r = new Random();

    public void start(View view){

        timer.start();
        binding.button.setVisibility(View.INVISIBLE);
        binding.textView1.setVisibility(View.VISIBLE);
        binding.textView2.setVisibility(View.VISIBLE);
        binding.textView3.setVisibility(View.VISIBLE);
        binding.textView4.setVisibility(View.VISIBLE);
        binding.timerTextView.setVisibility(View.VISIBLE);
        binding.scoreTextView.setVisibility(View.VISIBLE);
        createQues();
        binding.invalidateAll();

    }

    public void createQues(){
        int n1 = r.nextInt(20) + 1;
        int n2 = r.nextInt(20) + 1;
        String ques = n1 + " + " + n2;
        binding.quesTextView.setText(ques);
        int[] answers = new int[4];
        locationOfCorrectAns = r.nextInt(4);
        for(int i=0; i<4; i++){
            if(i==locationOfCorrectAns) {
                answers[i] = n1 + n2;
            }
            else{
                int wrongAns = r.nextInt(40) + 1;
                if(wrongAns != (n1 + n2))
                    answers[i] = wrongAns;
            }
        }
        binding.textView1.setText(String.valueOf(answers[0]));
        binding.textView2.setText(String.valueOf(answers[1]));
        binding.textView3.setText(String.valueOf(answers[2]));
        binding.textView4.setText(String.valueOf(answers[3]));
        binding.invalidateAll();


    }
    public void makeques(View view){

        int optionSelected = Integer.parseInt(view.getTag().toString());
        String result;
        if(optionSelected == locationOfCorrectAns){
            result = "Correct!";
            ansGuessedCorrect++;
        }
        else {
            result = "Wrong!";
        }
        binding.resultTextView.setText(result);
        quesCounter++;
        String score;
        if(quesCounter == 0){
            score = "0 / 0";
        }
        else {
            score = ansGuessedCorrect + " / " + quesCounter;
        }
        binding.scoreTextView.setText(score);
        createQues();
        binding.invalidateAll();

    }

    public void playAgain(View view){
        binding.playAgainButton.setVisibility(View.INVISIBLE);
        timer.start();
        binding.textView1.setClickable(true);
        binding.textView2.setClickable(true);
        binding.textView3.setClickable(true);
        binding.textView4.setClickable(true);
        binding.resultTextView.setText("");
        binding.scoreTextView.setText("0 / 0");
        quesCounter = 0;
        ansGuessedCorrect = 0;
        createQues();
        binding.invalidateAll();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        locationOfCorrectAns = r.nextInt(4);

        binding.textView1.setOnClickListener(this::makeques);
        binding.textView2.setOnClickListener(this::makeques);
        binding.textView3.setOnClickListener(this::makeques);
        binding.textView4.setOnClickListener(this::makeques);
        binding.button.setOnClickListener(this::start);
        binding.playAgainButton.setOnClickListener(this::playAgain);

        timer = new CountDownTimer(20100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = millisUntilFinished/1000 + "s";
                binding.timerTextView.setText(time);
            }

            @Override
            public void onFinish() {

                binding.resultTextView.setText(R.string.done);
                binding.textView1.setClickable(false);
                binding.textView2.setClickable(false);
                binding.textView3.setClickable(false);
                binding.textView4.setClickable(false);
                binding.playAgainButton.setVisibility(View.VISIBLE);

            }
        };

    }
}