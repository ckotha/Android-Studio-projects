package com.charan.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    EditText usernameText;
    EditText passwordText;
    Button signUpButton;
    Button loginButton;
    Boolean logIn;
    ImageView logoImageView;
    ConstraintLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        logoImageView = findViewById(R.id.logoImageView);
        backgroundLayout = findViewById(R.id.backgroundLayout);
        logIn = false;
        signUpButton.setText("Sign Up");
        loginButton.setText(("Or Log In"));
        passwordText.setOnKeyListener(this);

        if(ParseUser.getCurrentUser() != null){

            showUserList();

        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void showUserList(){

        Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(intent);

    }

    public void onClick(View v) {

        if(v.getId() == R.id.loginButton) {

            if (signUpButton.getText().equals("Sign Up")) {

                signUpButton.setText("Log In");
                loginButton.setText("Or Sign Up");
                logIn = true;

            } else {

                signUpButton.setText("Sign Up");
                loginButton.setText("Or Log In");
                logIn = false;

            }

        }else if(v.getId() == R.id.backgroundLayout || v.getId() == R.id.logoImageView){

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }

    }

    public void loginSignUp(View v) {

        ParseUser user = new ParseUser();


        if (!logIn && usernameText.getText() != null && passwordText.getText() != null) {

            user.setUsername(usernameText.getText().toString());
            user.setPassword(passwordText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        Log.i("Sign Up", "Successful");

                        showUserList();

                    } else {

                        Toast.makeText(MainActivity.this, e.toString().substring(e.toString().lastIndexOf(":") + 1), Toast.LENGTH_SHORT).show();
                        Log.i("Sign Up", "Failed");

                    }

                }
            });

        } else if (logIn && usernameText.getText() != null && passwordText.getText() != null) {

            ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (user != null) {

                        Log.i("Login", "Successful");

                        showUserList();

                    } else {

                        Toast.makeText(MainActivity.this, e.toString().substring(e.toString().lastIndexOf(":") + 1), Toast.LENGTH_SHORT).show();

                        Log.i("Login", "Failed " + e.toString());

                    }

                }
            });


        }

    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN) {

            loginSignUp(v);

        }

        return false;
    }
}

