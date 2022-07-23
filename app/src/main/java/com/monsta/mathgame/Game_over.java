package com.monsta.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game_over extends AppCompatActivity {

    TextView result;
    Button playAgain;
    Button exit;
    int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        result= findViewById(R.id.textViewResult);
        playAgain=findViewById(R.id.buttonAgain);
        exit= findViewById(R.id.buttonExit);

        Intent intent = getIntent();
        score=intent.getIntExtra("score",0);

        result.setText("Your Score "+score);


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game_over.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}