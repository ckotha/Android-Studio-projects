package com.charan.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.charan.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends  = new ArrayList<>();

        friends.add("Mia");
        friends.add("John");

        try {

            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

        } catch (IOException e) {

            e.printStackTrace();

        }

        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends =(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (IOException e) {

            e.printStackTrace();

        }

        Log.i("new friends", newFriends.toString());

        //sharedPreferences.edit().putString("username", "charan").apply();

        //String username = sharedPreferences.getString("username", "");

        //Log.i("username", username);
    }
}
