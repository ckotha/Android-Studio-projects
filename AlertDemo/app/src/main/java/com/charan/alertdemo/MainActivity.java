package com.charan.alertdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView languageText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.charan.alertdemo", Context.MODE_PRIVATE);

        SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove("language");
        editor.apply();

        languageText = findViewById(R.id.languageText);

        languageText.setText(sharedPreferences.getString("language",""));

        if(sharedPreferences.getString("language","").equals("")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like?")
                    .setPositiveButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(MainActivity.this, "Spanish", Toast.LENGTH_LONG).show();
                            sharedPreferences.edit().putString("language", "Spanish").apply();
                            languageText.setText(sharedPreferences.getString("language", ""));

                        }
                    })
                    .setNegativeButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(MainActivity.this, "English!", Toast.LENGTH_LONG).show();
                            sharedPreferences.edit().putString("language", "English").apply();
                            languageText.setText(sharedPreferences.getString("language", ""));


                        }
                    })
                    .show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case R.id.english:

                Log.i("Menu Item Selected", "English");
                sharedPreferences.edit().putString("language", "English").apply();
                languageText.setText(sharedPreferences.getString("language",""));
                return true;

            case R.id.spanish:

                Log.i("Menu Item Selected", "Spanish");
                sharedPreferences.edit().putString("language", "Spanish").apply();
                languageText.setText(sharedPreferences.getString("language",""));
                return true;

            default:
                return false;

        }
    }


}
