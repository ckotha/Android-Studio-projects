package com.charan.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public void fade(View view){
        ImageView spacex = (ImageView)findViewById(R.id.spacex);
        //ImageView nasa = (ImageView)findViewById(R.id.nasa);
        spacex.animate().translationXBy(300f).rotation(1800f).scaleX(1f).setDuration(500);
        //nasa.animate().alpha(1f).setDuration(2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView spacex = (ImageView)findViewById(R.id.spacex);
        spacex.setTranslationX(-300f);
        spacex.setScaleX(0.5f);

    }
}
