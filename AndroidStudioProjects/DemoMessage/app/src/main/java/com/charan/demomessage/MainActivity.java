package com.charan.demomessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button myButton;
    public void clickFunction(View view){
        ImageView image = (ImageView) findViewById(R.id.myImage);
        image.setImageResource(R.drawable.cat2);
        Log.i("Info","Button Clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
