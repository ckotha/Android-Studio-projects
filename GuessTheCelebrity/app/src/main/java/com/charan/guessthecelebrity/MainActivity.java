package com.charan.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button op1Button;
    Button op2Button;
    Button op3Button;
    Button op4Button;

    public class ImageDownloader extends AsyncTask<String, Void, String> {//Bitmap is for the image

        @Override
        protected String doInBackground(String... urls) {
            try {

                String contents = "";

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();//gets the char value of the character

                while(data != -1){//if there is still data or not

                    char current = (char) data;

                    contents += current;

                    data = reader.read();

                }

                Log.i("progress", "converted to string");

                //Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return contents;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        op1Button = findViewById(R.id.op1Button);
        op1Button.setTag(0);

        op2Button = findViewById(R.id.op2Button);
        op2Button.setTag(0);

        op3Button = findViewById(R.id.op3Button);
        op3Button.setTag(0);

        op4Button = findViewById(R.id.op4Button);
        op4Button.setTag(0);

        img = findViewById((R.id.imageView1));

        ImageDownloader task = new ImageDownloader();
        String result = null;
        try {
            result = task.execute("http://www.posh24.se/kandisar").get();

            String[] splitResult = result.split("<div class = \"sidebarContainer\">");

            Pattern p = Pattern.compile("src=\"(.&?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while(m.find()){

                System.out.println(m.group(1));

            }

            p = Pattern.compile("alt=\"(.&?)\"");
            m = p.matcher(splitResult[0]);

            while(m.find()){

                System.out.println(m.group(1));

            }

        } catch (ExecutionException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }
}
