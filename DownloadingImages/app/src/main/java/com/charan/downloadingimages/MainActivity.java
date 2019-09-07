package com.charan.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView img;

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{//Bitmap is for the image

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }

    public void downloadImage(View view){
        //https://ih1.redbubble.net/image.754208203.5375/mp,840x830,matte,f8f8f8,t-pad,750x1000,f8f8f8.jpg

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {

            myImage = task.execute("https://pbs.twimg.com/media/DzL1XRuU8AAJ2EF.jpg").get();

            img.setImageBitmap(myImage);
        } catch (Exception e) {

            e.printStackTrace();

        }
        Log.i("Interaction", "Button Tapped");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById((R.id.imageView1));
    }
}
