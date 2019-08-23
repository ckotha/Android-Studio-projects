package com.charan.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;


    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;

    }


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

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void controlTimer(View view){
        if(counterIsActive == false) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            Log.i("Button Pressed", "Pressed");
             countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();
        }else{
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.seekBar);
        timerTextView = (TextView)findViewById(R.id.textView1);
        controllerButton = (Button)findViewById(R.id.button1);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);



        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
