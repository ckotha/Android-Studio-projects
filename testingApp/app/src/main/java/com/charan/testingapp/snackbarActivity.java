package com.charan.testingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class snackbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        //SnackBar stuff
        // Localization with the strings as well. look at strings.xml
        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinateLayout);

        Snackbar snackbar = Snackbar.make(coordinatorLayout, R.string.snackbar, Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.Undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),R.string.Undo, Toast.LENGTH_SHORT).show();

            }
        });
        snackbar.show();
    }
}
