package com.charan.bluetoothdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter BA;

    public void turnBluetoothOff(View view){

        BA.disable();

        if(BA.isEnabled()){

            Toast.makeText(this, "Bluetooth could not be disabled!", Toast.LENGTH_SHORT).show();


        }else {

            Toast.makeText(this, "Bluetooth off!", Toast.LENGTH_SHORT).show();
        }
    }

    public void findDevices(View view){

        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(intent);

    }

    public void viewDevices(View view){

        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();

        ListView pairedDevicesListView = findViewById(R.id.pairedDevicesListView);

        ArrayList pairedDevicesArrayList = new ArrayList();

        for(BluetoothDevice device : pairedDevices){

            pairedDevicesArrayList.add(device.getName());

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairedDevicesArrayList );

        pairedDevicesListView.setAdapter(arrayAdapter);

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BA = BluetoothAdapter.getDefaultAdapter();

        if(BA.isEnabled()){

            Toast.makeText(this, "Bluetooth on!", Toast.LENGTH_SHORT).show();

        }else{

          Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
          startActivity(i);

          if(BA.isEnabled()){

              Toast.makeText(this, "Bluetooth turned on!", Toast.LENGTH_SHORT).show();

          }
        }
    }
}
