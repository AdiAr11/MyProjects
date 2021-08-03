package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0 - yellow
    //1 - eth
    //2 - empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int activePlayer=0;
    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500f);
        TextView winner =(TextView) findViewById(R.id.textView);

        Button playAgainButton = (Button) findViewById(R.id.button);

        if(gameState[Integer.parseInt(counter.getTag().toString())] == 2 && gameActive){

            gameState[Integer.parseInt(counter.getTag().toString())] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.eth);
                activePlayer = 0;
            }

            counter.animate().rotation(1800).translationYBy(1500f);
            String name;

            for (int[] winPos : winPos) {
                if (gameState[winPos[0]] != 2 && gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]]) {

                    if (activePlayer == 1)
                        name = "Yellow";
                    else
                        name = "Etherium";
                    
                    gameActive = false;
                    winner.setText(String.format("%s has won", name));
                    playAgainButton.setVisibility(View.VISIBLE);
                    winner.setVisibility(View.VISIBLE);
                }
            }

        }
        
    }

    public void playAgain(View view)
    {
        TextView winner =(TextView) findViewById(R.id.textView);
        Button playAgainButton = (Button) findViewById(R.id.button);
        playAgainButton.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);

        ((ImageView) findViewById(R.id.imageView0)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView1)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView3)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView4)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView5)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView6)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView7)).setImageDrawable(null);
        ((ImageView) findViewById(R.id.imageView8)).setImageDrawable(null);


        Arrays.fill(gameState, 2);
        activePlayer=0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activePlayer = 0;
    }
}