package com.charan.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timesTableSeekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        ListView timesTableListView = (ListView) findViewById(R.id.timesTableListView);

        timesTableSeekBar.setMax(20);
        timesTableSeekBar.setProgress(10);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int minimum = 1;
                int timesTable;
                if (i < minimum){
                    timesTable = minimum;

                }else{
                    timesTable = i;

                }

                Log.i("SeekBar value", Integer.toString(timesTable));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
