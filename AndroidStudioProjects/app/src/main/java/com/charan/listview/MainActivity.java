package com.charan.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);

        final ArrayList<String> friends = new ArrayList<>(Arrays.asList("Mia", "John", "Andrew","Anthony"));

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friends);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(getApplicationContext(), "Person: " + friends.get(i), Toast.LENGTH_SHORT);
                toast.show();
            }
        });



    }
}
