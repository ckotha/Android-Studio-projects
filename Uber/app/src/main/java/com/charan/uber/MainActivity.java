package com.charan.uber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button riderButton;
    Button driverButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if(ParseUser.getCurrentUser() == null){

            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null){

                        Log.i("Info", "Anonymous Login successful");

                    }else{

                        Log.i("Info", "Anonymous Login failed");

                    }
                }
            });

        }else{


            if(ParseUser.getCurrentUser().get("isDriver") != null){

                Log.i("Info", ParseUser.getCurrentUser().get("isDriver").toString());
                switchActivity();

            }

        }
        riderButton = findViewById(R.id.riderButton);
        driverButton = findViewById(R.id.driverButton);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void onClick(View view){
        Boolean isDriver = false;

        if(view.getId() == driverButton.getId()){

            Log.i("Info","Driver");
            isDriver = true;
            Log.i("Info",isDriver.toString());


        }else if(view.getId() == riderButton.getId()){

            Log.i("Info","Rider");

        }

        ParseUser.getCurrentUser().put("isDriver", isDriver);

        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                switchActivity();

            }
        });
    }

    public void switchActivity(){

        if(ParseUser.getCurrentUser().get("isDriver").equals(false)){

            Intent intent = new Intent(getApplicationContext(),RiderActivity.class);
            startActivity(intent);

        }else{

            Intent intent = new Intent(getApplicationContext(),ViewRequestsActivity.class);
            startActivity(intent);

        }



    }
}


