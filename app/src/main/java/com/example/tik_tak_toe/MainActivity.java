package com.example.tik_tak_toe;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button rest;
    ImageButton Button0, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8;
    TextView homeScore, awayScore, winText;
    //true = red, false = yellow
    boolean home;
    LinearLayout layout, fullTable;

    //2 means nothing is inside, 0=red, 1=yellow
    int table[]= {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int scoreHome=0;
    int scoreAway=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeScore= (TextView) findViewById(R.id.homeScore);
        awayScore= (TextView) findViewById(R.id.awayScore);
        winText= (TextView) findViewById(R.id.winText);
        home=true;
        layout=(LinearLayout)findViewById(R.id.newGameLayout);
        fullTable=(LinearLayout)findViewById(R.id.fullTable);
        Button0 = (ImageButton) findViewById(R.id.number1);
        Button1 = (ImageButton) findViewById(R.id.number2);
        Button2 = (ImageButton) findViewById(R.id.number3);
        Button3 = (ImageButton) findViewById(R.id.number4);
        Button4 = (ImageButton) findViewById(R.id.number5);
        Button5 = (ImageButton) findViewById(R.id.number6);
        Button6 = (ImageButton) findViewById(R.id.number7);
        Button7 = (ImageButton) findViewById(R.id.number8);
        Button8 = (ImageButton) findViewById(R.id.number9);
    }

    public void selection(View view){
        ImageButton counter= (ImageButton) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(table[tappedCounter]==2) {
            counter.setTranslationY(-1000f);
            if (home) {
                counter.setImageResource(R.drawable.red);
                table[tappedCounter]=1;
            }else {
                counter.setImageResource(R.drawable.yellow);
                table[tappedCounter]=0;
            }
            counter.setAlpha(1f);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(600);
            checkIfWin();
            home = !home;
            checkIfFull();
        }

    }
    private void checkIfWin(){
        for(int[] winningPosition: winningPositions){
            if(table[winningPosition[0]]==table[winningPosition[1]] && table[winningPosition[0]]==table[winningPosition[2]] && table[winningPosition[0]]!=2){
                if(home){
                    scoreHome++;
                    homeScore.setText(Integer.toString(scoreHome));
                    winText.setText("HOME WIIINS");
                }else {
                    scoreAway++;
                    awayScore.setText(Integer.toString(scoreAway));
                    winText.setText("AWAY WIIINS");
                }
                fullTable.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);
            }
        }
    }
    private void checkIfFull(){
        boolean check=false;
        for(int i=0; i<table.length;i++){
            if(table[i]==2)
                check=true;
        }
        if(check==false){
            winText.setText("YOU TIED:)");
            fullTable.setVisibility(View.INVISIBLE);
            layout.setVisibility(View.VISIBLE);
        }
    }


    private void reset(View view){
        newGame();
        scoreAway=0;
        scoreHome=0;
        awayScore.setText(Integer.toString(scoreAway));
        homeScore.setText(Integer.toString(scoreHome));

        if(layout.getVisibility()==View.VISIBLE){
            layout.setVisibility(View.INVISIBLE);
            fullTable.setVisibility(View.VISIBLE);
        }
    }
    private void newGameButton(View view){
        newGame();
        layout.setVisibility(View.INVISIBLE);
        fullTable.setVisibility(View.VISIBLE);
    }

    private void newGame(){
        Button0.setAlpha(0f);
        Button1.setAlpha(0f);
        Button2.setAlpha(0f);
        Button3.setAlpha(0f);
        Button4.setAlpha(0f);
        Button5.setAlpha(0f);
        Button6.setAlpha(0f);
        Button7.setAlpha(0f);
        Button8.setAlpha(0f);
        for(int i=0; i<table.length;i++){
            table[i]=2;
        }

    }
}
