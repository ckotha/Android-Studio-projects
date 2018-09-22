package com.charan.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int correctNum;
    public void clickFunction(View view){
        EditText numberEditText = (EditText)findViewById(R.id.numberEditText);
        int userNum =  Integer.valueOf(numberEditText.getText().toString());
        if(userNum == correctNum){
            Toast.makeText(getApplicationContext(), "You got it!", Toast.LENGTH_LONG).show();
        }else if(userNum > correctNum){
            Toast.makeText(getApplicationContext(), "Try lower!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Try higher!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        correctNum = rand.nextInt(20) + 1;
    }
}
