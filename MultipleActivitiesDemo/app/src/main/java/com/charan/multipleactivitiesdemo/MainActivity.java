package com.charan.multipleactivitiesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Old way to do a table
        ListView table =  findViewById(R.id.table);

        final ArrayList<String> friends = new ArrayList<String>();

        friends.add("Mia");
        friends.add("Andrew");
        friends.add("John");
        friends.add("Anthony");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, friends);

        table.setAdapter(arrayAdapter);

        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("name", friends.get(position));
                startActivity(intent);
            }
        });
    }
}
