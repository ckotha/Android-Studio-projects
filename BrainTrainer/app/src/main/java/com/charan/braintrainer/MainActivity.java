package com.charan.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    TextView correctText;
    TextView questionText;
    TextView answerText;
    TextView t1;
    TextView t2;
    TextView t3;
    Button op1Button;
    Button op2Button;
    Button op3Button;
    Button op4Button;
    Button goButton;
    boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    int op;
    int x;
    int y;
    int correct = 0;
    int total = 0;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if(seconds == 0){
            secondString = "00";
        }
        else if(seconds == 1 || seconds == 2  || seconds == 3  || seconds == 4  || seconds == 5  || seconds == 6  || seconds == 7  || seconds == 8  || seconds == 9 ) {
            secondString = "0" + Integer.toString(seconds);
        }

        timerText.setText(Integer.toString(minutes) + ":" + secondString + "s");
    }

    public void resetTimer(){
        showHide(false);
        timerText.setText("0:30s");
        countDownTimer.cancel();
        counterIsActive = false;
        correct = 0;
        total = 0;
    }

    public void controlTimer(View view){
        if(counterIsActive == false) {
            questionGenerator();
            counterIsActive = true;
            showHide(true);
            countDownTimer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);//make this user modifiable
                }

                @Override
                public void onFinish() {
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();
        }
    }

    public void questionGenerator(){
        x = (int)(Math.random() * 100.0);
        y = (int)(Math.random() * 100.0);
        op = (int)(Math.random() * 3.0);

        Button[] buttons = new Button[]{op1Button,op2Button,op3Button,op4Button};
        questionText.setText(x + " + " + y);

        for(int i = 0; i < 4; i++){
            if(op == i){
                buttons[i].setText(String.valueOf(x+y));
                buttons[i].setTag(1);
            }else {
                buttons[i].setText(String.valueOf((int)((x+y) * (Math.random() * 2.0))));
            }
        }
    }

    public void onClick(View view){
        if(view.getTag().equals(1)) {
            answerText.setText("Correct!");
            view.setTag(0);
            correct++;
            total++;
            correctText.setText(correct + " / " + total);
            questionGenerator();

        }else{
            answerText.setText("Incorrect!");
            total++;
            correctText.setText(correct + " / " + total);
            questionGenerator();
        }
    }

    public void showHide(boolean show){
        if(!show){
            goButton.setVisibility(View.VISIBLE);

            timerText.setVisibility(View.GONE);
            correctText.setVisibility(View.GONE);
            questionText.setVisibility(View.GONE);
            t1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            answerText.setVisibility(View.GONE);
            op1Button.setVisibility(View.GONE);
            op2Button.setVisibility(View.GONE);
            op3Button.setVisibility(View.GONE);
            op4Button.setVisibility(View.GONE);

        }else{
            goButton.setVisibility(View.GONE);

            timerText.setVisibility(View.VISIBLE);
            correctText.setVisibility(View.VISIBLE);
            questionText.setVisibility(View.VISIBLE);
            answerText.setVisibility(View.VISIBLE);
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);
            t3.setVisibility(View.VISIBLE);
            op1Button.setVisibility(View.VISIBLE);
            op2Button.setVisibility(View.VISIBLE);
            op3Button.setVisibility(View.VISIBLE);
            op4Button.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timerText);
        correctText = findViewById(R.id.correctText);
        questionText = findViewById(R.id.questionText);
        answerText = findViewById(R.id.answerText);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);


        op1Button = findViewById(R.id.op1Button);
        op1Button.setTag(0);

        op2Button = findViewById(R.id.op2Button);
        op2Button.setTag(0);

        op3Button = findViewById(R.id.op3Button);
        op3Button.setTag(0);

        op4Button = findViewById(R.id.op4Button);
        op4Button.setTag(0);

        goButton = findViewById(R.id.goButton);
        correctText.setText(correct + " / " + total);
        showHide(false);

    }


}
