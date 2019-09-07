package com.charan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText locationText;
    TextView weatherText;

    public class DownloadTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try {
                url = new URL(urls[0]);

                connection = (HttpURLConnection) url.openConnection();

                InputStream in = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();//gets the char value of the character

                while(data != -1){//if there is still data or not

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (Exception e) {

                //Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                String weather = "";

                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather Content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                for(int i = 0; i < arr.length(); i++){

                    JSONObject jsonPart = arr.getJSONObject(i);

                    if(jsonPart.getString("main") != "" && jsonPart.getString("description") != "") {

                        weather += jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\r\n";

                    }

                }

                if (weather != "") {

                    weatherText.setText(weather);
                    Log.i("Weather", weather);

                }else{

                    Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);

                }
            } catch (JSONException e) {

                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
                e.printStackTrace();

            }
            catch (Exception e) {

                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
                e.printStackTrace();

            }

        }
    }

    public void getLocation(View v){
        String location = "";
        String result = "";

        location = locationText.getText().toString();
        InputMethodManager mgr = (InputMethodManager)  getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(locationText.getWindowToken(), 0);
        try {

            String encodedCityName = URLEncoder.encode(location, "UTF-8");
            DownloadTask task = new DownloadTask();
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&APPID=cc5a5386a3cd6ca165dc2da5148ae275");
            //task.execute("http://api.openweathermap.org/data/2.5/weather?q=London&APPID=cc5a5386a3cd6ca165dc2da5148ae275");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationText = (EditText)findViewById(R.id.locationText);
        weatherText = (TextView)findViewById(R.id.weatherText);

    }
}
