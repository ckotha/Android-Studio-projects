package com.charan.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        EditText myTextField = (EditText)findViewById(R.id.editCurrencyText);
        double d = Double.parseDouble(myTextField.getText().toString());
        Toast.makeText(getApplicationContext(),String.valueOf(d*72.10) + " rupees!",Toast.LENGTH_LONG).show();
        Log.i("Info",String.valueOf(d*72.10));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
