package com.example.bordia98.scarnesdie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static com.example.bordia98.scarnesdie.R.drawable.dice1;

public class MainActivity extends AppCompatActivity {

    private static int userOverallScore, userTurnScore, cpuOverallScore, cpuTurnScore;
    private static final int WIN_SCORE = 100;

    private static TextView playerscore, computerscore ,pturnscore,cpturnscore,result;
    private static Button roll, hold, reset;
    private static ImageView diceface;

    private static int diceFaces[] = {
            dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };

    private static Random random = new Random();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         playerscore=(TextView)findViewById(R.id.playerscore);
        result=(TextView)findViewById(R.id.result);

        computerscore = (TextView) findViewById(R.id.computerscore);
         diceface = (ImageView) findViewById(R.id.diceimage);
         hold=(Button)findViewById(R.id.holdbutton);
         reset =(Button) findViewById(R.id.resetbutton);
         roll = (Button) findViewById(R.id.rollbutton);
         pturnscore=(TextView)findViewById(R.id.pturnscore);
         cpturnscore =(TextView)findViewById(R.id.cpturnscore);
        result.setText("");

       // userOverallScore=userTurnScore=cpuOverallScore=cpuTurnScore=0;
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onhold();
            }
        });

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onroll();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k=0;
                pturnscore.setText(k+"");
                cpturnscore.setText(k+"");
                playerscore.setText(k+"");
                computerscore.setText(k+"");
                result.setText("");
                roll.setEnabled(true);
                hold.setEnabled(true);

            }
        });
    }

    public void onhold() {

        userTurnScore=0;
        updatescore();
        cpuTurnScore=0;

        computerturn();
    }

    public void onroll(){
        cpuTurnScore=0;
        int dicenumber=random.nextInt(6)+1;

        if (dicenumber==1){
            diceface.setImageResource(R.drawable.dice1);
            userOverallScore = Integer.parseInt(playerscore.getText().toString());
            userOverallScore-=userTurnScore;
            playerscore.setText(userOverallScore+"");
            userTurnScore=0;
            updatescore();

            roll.setEnabled(false);
            computerturn();
        }
        else{

            diceface.setImageResource(diceFaces[dicenumber-1]);
            userTurnScore=Integer.parseInt(pturnscore.getText().toString());
            userTurnScore+=dicenumber;
            updatescore();
                     

        }

    }

    private void computerturn() {
        roll.setEnabled(false);
        hold.setEnabled(false);
        userTurnScore=0;
        cpuTurnScore=0;

        do {
            int dicenumber = random.nextInt(6) + 1;

            if (dicenumber == 1) {
                diceface.setImageResource(diceFaces[dicenumber - 1]);
                cpuOverallScore = Integer.parseInt(computerscore.getText().toString());
                cpuOverallScore -= cpuTurnScore;
                computerscore.setText(cpuOverallScore+"");
                cpuTurnScore = 0;
                updatescore();
                roll.setEnabled(true);
                hold.setEnabled(true);
                break;
            } else {
                diceface.setImageResource(diceFaces[dicenumber - 1]);
                cpuTurnScore = Integer.parseInt(cpturnscore.getText().toString());
                cpuTurnScore += dicenumber;
                updatescore();

            }
        }while (cpuTurnScore<=15);

        cpuTurnScore = 0;
        updatescore();
        roll.setEnabled(true);
        hold.setEnabled(true);


    }

    private void updatescore() {
        pturnscore.setText(userTurnScore+"");
        cpturnscore.setText(cpuTurnScore+"");
        userOverallScore = Integer.parseInt(playerscore.getText().toString());
        cpuOverallScore=Integer.parseInt(computerscore.getText().toString());
        int k=userOverallScore+userTurnScore;
        playerscore.setText(k+"");
        int y=cpuOverallScore+cpuTurnScore;
        computerscore.setText(y+"");
        if(k>=100){

            result.setText("Player is the winner");
            roll.setEnabled(false);
            hold.setEnabled(false);
            return;
        }
        else if(y>=100){
            roll.setEnabled(false);
            hold.setEnabled(false);
            result.setText("Computer is the winner");
            return;
        }
    }


}
