package com.charan.demotextfield;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){

        EditText myTextField = (EditText)findViewById(R.id.myText1);
        EditText myTextField2 = (EditText)findViewById(R.id.myText2);

        Log.i("Info",myTextField.getText().toString());
        Log.i("Info",myTextField2.getText().toString());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
