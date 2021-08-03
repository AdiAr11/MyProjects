package com.example.trivia;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.Controller.Singleton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView quesTextView, quesNoTextView, scoreTextView,highestScoreTextView;
    boolean Answer;
    int score = 0;
    int highest_score;
    int quesCounter = 0;
    Button nextButton;
    final String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quesTextView = findViewById(R.id.QuestionTextView);
        quesNoTextView = findViewById(R.id.QuestionNoTextView);
        nextButton = findViewById(R.id.nextButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        highestScoreTextView = findViewById(R.id.highestScoreTextView);
        quesTextView.setTextColor(Color.WHITE);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        newQues();
        highest_score = sharedPreferences.getInt("highest_score", 0);
        String highestScore = "Highest Score: " + highest_score;
        highestScoreTextView.setText(highestScore);

        nextButton.setOnClickListener(v -> newQues());
    }

    public void AnswerClicked(View view) {

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (view.getTag().toString().equals(String.valueOf(Answer))) {
            quesTextView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
            quesTextView.setTextColor(Color.GREEN);
            score += 2;
            if(score>highest_score) {
                editor.putInt("highest_score", score);
                editor.apply();
            }
            Snackbar.make(view, "Correct", Snackbar.LENGTH_SHORT).show();
        } else {
            quesTextView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
            quesTextView.setTextColor(Color.RED);
            score--;
            Snackbar.make(view, "Incorrect", Snackbar.LENGTH_SHORT).show();
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            quesTextView.setTextColor(Color.WHITE);
            newQues();
        }, 1100);
        if(score<0)
            score = 0;
        String s = "Score: " + score;
        scoreTextView.setText(s);

    }



    public void newQues(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {

            try {
                    String str = response.getJSONArray(quesCounter).get(0).toString();
                    String quesCount = "Question: " + quesCounter + " / " + response.length();
                    quesNoTextView.setText(quesCount);
                    Answer = response.getJSONArray(quesCounter).getBoolean(1);
                    quesTextView.setText(str);
                    quesCounter++;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Log.d("Json", "Error"));

        Singleton.getInstance(this).addToRequestQueue(jsonArrayRequest);

    }
}


