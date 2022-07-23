package com.monsta.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class Game_Addition extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;


    TextView question;
    EditText answer;

    Button ok;
    Button next;



    Random random = new Random();
    int number1;
    int number2;
    int userAnswer;
    int realAnswer;
    int userScore =0;
    int userLife = 3;


    CountDownTimer timer;
    private static final long START_TIMER_IN_MILES = 60000;  // INITIAL TIME 60 SECONDS  // FINAL VALUE SHOULD BE WRITTEN IN CAPITAL
    Boolean timer_running;
    long time_left_in_miles = START_TIMER_IN_MILES;

     int operator =0;

    // this part is for operator




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_addition);




        score=findViewById(R.id.textViewScore);
        life=findViewById(R.id.textViewLife);
        time=findViewById(R.id.textViewTime);

        question = findViewById(R.id.textViewQuestion);
        answer=findViewById(R.id.editTextAnswer);

        ok = findViewById(R.id.buttonOk);
        next= findViewById(R.id.buttonNext);

        Intent intent_operator= getIntent();
        operator = intent_operator.getIntExtra("operator",1);


        gameContinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseTimer();

                userAnswer = Integer.valueOf(answer.getText().toString());
                if (userAnswer==realAnswer){
                    userScore += 10;
                    score.setText("" + userScore);
                    question.setText("Congratulation , Your answer is true!");
                }
                else
                {
                    userLife--;
                    life.setText("" + userLife);
                    question.setText("Sorry, Your answer is false");

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                resetTimer();
                if(userLife!=0){
                    answer.setText("");
                    gameContinue();
                }
                else{
                    Toast.makeText(Game_Addition.this, "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Game_Addition.this,Game_over.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    public void gameContinue(){



        number1 = random.nextInt(100);
        number2 = random.nextInt(100);



        if(operator==1){
        question.setText(number1 + " + " + number2);
        realAnswer = number1 + number2;
        startTimer();
        }

        else if (operator==2){
            question.setText(number1 + " - " + number2);
            realAnswer = number1 - number2;
            startTimer();
        }
        else{
            question.setText(number1 + " X " + number2);
            realAnswer = number1 * number2;
            startTimer();
        }

    }

    public void startTimer(){
        timer = new CountDownTimer(time_left_in_miles,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_miles = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                userLife--;
                life.setText("" + userLife);
                question.setText("Sorry! ,Time is up!");
            }
        }.start();

        timer_running=true;
    }

    public void updateText(){
        int second  = (int)(time_left_in_miles/1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }

    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;
    }

    public void resetTimer(){
        time_left_in_miles = START_TIMER_IN_MILES;
        updateText();
    }

}