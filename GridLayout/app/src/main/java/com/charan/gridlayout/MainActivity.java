package com.charan.gridlayout;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    MediaPlayer mediaPlayer4;
    MediaPlayer mediaPlayer5;
    MediaPlayer mediaPlayer6;
    MediaPlayer mediaPlayer7;
    MediaPlayer mediaPlayer8;
    MediaPlayer[] mediaPlayers;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer1 = MediaPlayer.create(this,R.raw.doyouspeakenglish);
        mediaPlayer2 = MediaPlayer.create(this,R.raw.goodevening);
        mediaPlayer3 = MediaPlayer.create(this,R.raw.hello);
        mediaPlayer4 = MediaPlayer.create(this,R.raw.howareyou);
        mediaPlayer5 = MediaPlayer.create(this,R.raw.ilivein);
        mediaPlayer6 = MediaPlayer.create(this,R.raw.mynameis);
        mediaPlayer7 = MediaPlayer.create(this,R.raw.please);
        mediaPlayer8 = MediaPlayer.create(this,R.raw.welcome);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);

        mediaPlayers = new MediaPlayer[]{mediaPlayer1,mediaPlayer2,mediaPlayer3,mediaPlayer4,mediaPlayer5,mediaPlayer6,mediaPlayer7,mediaPlayer8};

        buttons = new Button[]{button1,button2,button3,button4,button5,button6,button7,button8};
    }

    public void playSound(View view){
        if(!mediaPlayer1.isPlaying() || !mediaPlayer2.isPlaying() ||!mediaPlayer3.isPlaying() ||!mediaPlayer4.isPlaying() ||!mediaPlayer5.isPlaying() ||!mediaPlayer6.isPlaying() ||!mediaPlayer7.isPlaying() ||!mediaPlayer8.isPlaying()){

            for(int i = 0; i < buttons.length; i++){

                if(view == buttons[i]){
                    mediaPlayers[i].start();
                }

            }

        }

    }
}
