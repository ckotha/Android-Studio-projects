package com.charan.triorsquare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    class Number{

        int testNumber;
        boolean isTri = false;
        boolean isSquare = false;

        public boolean isSquare(){
            double squareRoot = Math.sqrt(testNumber);
            if(((squareRoot - Math.floor(squareRoot)) == 0))
                isSquare = true;
            return isSquare;
        }

        public boolean isTri(){
            double squareRoot = Math.sqrt(8*testNumber+1);
            if(((squareRoot - Math.floor(squareRoot)) == 0))
                isTri = true;
            return isTri;
        }
    }


    public void clickFunction(View view){
        EditText editTextNumber = (EditText)findViewById(R.id.editTextNumber);
        if(editTextNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter number", Toast.LENGTH_LONG).show();
        }else {
            Number num = new Number();
            num.testNumber = Integer.valueOf(editTextNumber.getText().toString());
            if (num.isSquare()) {
                Toast.makeText(getApplicationContext(), "The number is square!", Toast.LENGTH_LONG).show();
            } else if (num.isTri()) {
                Toast.makeText(getApplicationContext(), "The number is triangular!", Toast.LENGTH_LONG).show();
            } else if (num.isSquare() && num.isTri()) {
                Toast.makeText(getApplicationContext(), "The number is square and triangular!!", Toast.LENGTH_LONG).show();
            } else if (!(num.isSquare() || num.isTri())) {
                Toast.makeText(getApplicationContext(), "The number is neither square or triangular!", Toast.LENGTH_LONG).show();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
