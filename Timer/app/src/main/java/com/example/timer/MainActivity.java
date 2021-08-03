package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    SeekBar timerSeekbar;
    TextView timerCountDown;
    Button go;
    boolean isCounterActive = false;
    int currentTime;
    MediaPlayer mediaPlayer ;
    long currentTimeInMilli;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // **************
        currentTime = 10;
        go = findViewById(R.id.button);
        timerSeekbar = findViewById(R.id.seekBar);
        timerCountDown = findViewById(R.id.textView);
        currentTimeInMilli = (long) currentTime * 1000;


        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(currentTime);
        updateTextView(String.valueOf(currentTime));

        mediaPlayer = MediaPlayer.create(this, R.raw.air_horn);


        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentTime = progress;
                updateTextView(String.valueOf(currentTime));
                currentTimeInMilli = currentTime * 1000L;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    //****************

    public void updateTextView(String toThis) {

        int min = Integer.parseInt(toThis)/60;
        int sec = Integer.parseInt(toThis) % 60;
        String timeLeft;
        if(sec<10){
            timeLeft = min + " : 0" + sec;
        }
        else
            timeLeft = min + " : " + sec;

        timerCountDown.setText(timeLeft);
    }

    //*****

    public void startTimer(View view)
    {
        if(!isCounterActive) {
            countDown(currentTimeInMilli);
            countDownTimer.start();
            isCounterActive = true;
            timerSeekbar.setEnabled(false);
            go.setText("Stop");
        }

        else{
            resetTimer();
        }

    }

    public void resetTimer(){
        countDownTimer.cancel();
        timerCountDown.setText("0 : 10");
        timerSeekbar.setProgress(10);
        isCounterActive = false;
        timerSeekbar.setEnabled(true);
        go.setText("Go !");
    }


    public void countDown(long currentTimeInMilli){

        countDownTimer = new CountDownTimer(currentTimeInMilli + 700, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                currentTime = (int)(millisUntilFinished/1000);
                updateTextView(String.valueOf(currentTime));
                timerSeekbar.setProgress((int)millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
                resetTimer();
            }
        };

    }


}