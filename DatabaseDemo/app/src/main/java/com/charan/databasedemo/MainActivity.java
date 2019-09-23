package com.charan.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase eventDatabase = this.openOrCreateDatabase("users", MODE_PRIVATE,null);

            eventDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");

            //eventDatabase.execSQL("INSERT INTO newUsers(name, age) VALUES ('Mia', 21), ('Grandpa', 72), ('Stella', 4), ('Charan', 20)");

            //eventDatabase.execSQL("DELETE FROM newUsers WHERE id = 1 ");

            //eventDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, age INT(3))");

            //eventDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Mia', 21), ('Grandpa', 72), ('Stella', 4), ('Charan', 20)");

            //eventDatabase.execSQL("DELETE FROM users WHERE name = 'Mia' ");

            //eventDatabase.execSQL("UPDATE users SET age = 21 WHERE name = 'Charan'");


            Cursor c = eventDatabase.rawQuery("SELECT * FROM newUsers",null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();
            while(c != null){

                Log.i("name", c.getString(nameIndex));
                Log.i("age", c.getString(ageIndex));
                Log.i("age", c.getString(idIndex));

                c.moveToNext();

            }

        }catch(Exception e){


        }
    }
}
