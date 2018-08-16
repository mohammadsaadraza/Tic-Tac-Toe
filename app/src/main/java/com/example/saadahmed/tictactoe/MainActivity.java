package com.example.saadahmed.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public boolean gameActive=true;
    int[]gameState={2,2,2,2,2,2,2,2,2};
    int[][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    String message;

    public void dropIn(View view){
        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameActive==true) {

            gameState[tappedCounter]=activePlayer;
            if (activePlayer == 0) {
                counter.animate().translationYBy(-1500);
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                counter.animate().translationYBy(10).rotation(3600).setDuration(300);
            } else {
                counter.animate().translationYBy(-1500);
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                counter.animate().translationYBy(10).rotation(3600).setDuration(300);
            }
            if (checkWin())
                return;
            else
                checkDraw();

        }


    }
    public boolean checkWin(){
        for(int[]winningPosition:winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[1]] != 2){
                gameActive = false;
                if (activePlayer==1)
                    message="Yellow";
                else
                    message="Red";
                Toast.makeText(this,message+" has WON!!!",Toast.LENGTH_LONG).show();
                Button b= findViewById(R.id.playAgain);
                b.setVisibility(View.VISIBLE);
                return true;
            }


        }
        return false;
    }
    public boolean checkDraw(){
        int check=0;
        for (int i=0;i<gameState.length;i++){
            if (gameState[i]!=2)
                check+=1;
        }
        if (check==9){
            gameActive=false;
            message="DRAW!!!";
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            Button b= findViewById(R.id.playAgain);
            b.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }
    public void playAgain(View view){
        Button b = (Button) findViewById(R.id.playAgain);
        b.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout gd= (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i<gd.getChildCount();i++){
            ImageView im=(ImageView)gd.getChildAt(i);
            im.setImageDrawable(null);

        }
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        activePlayer=0;
        gameActive=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
