package com.monsta.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addition;
    Button subtraction;
    Button multi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition=findViewById(R.id.buttonAdd);
        subtraction=findViewById(R.id.buttonSub);
        multi=findViewById(R.id.buttonMulti);

        Intent intent = new Intent(MainActivity.this,Game_Addition.class);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("operator",1);
                startActivity(intent);
                finish();
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("operator",2);
                startActivity(intent);
                finish();
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("operator",3);
                startActivity(intent);
                finish();
            }
        });
    }
}