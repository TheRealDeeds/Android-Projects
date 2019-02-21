package com.example.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = findViewById(R.id.imageView);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    public void clickDownloadImage(View view) {
        // https://www.maxim.com/.image/t_share/MTM2MzQyNjQ0ODE2MDk0ODM1/f-35-publicjpg.jpg

        ImageDownloader task = new ImageDownloader();
        Bitmap bitmap;

        try {

            bitmap = task.execute("https://www.maxim.com/.image/t_share/MTM2MzQyNjQ0ODE2MDk0ODM1/f-35-publicjpg.jpg").get();
            downloadedImage.setImageBitmap(bitmap);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Button", "Clicked");
    }
}
