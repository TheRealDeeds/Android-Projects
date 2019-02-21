package com.example.toppaidcelebrities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String result;
    TextView textViewInfo;
    ImageView imageView;
    Button buttonCeleb;
    List<String> celebList;
    List<String> imageList;
    List<String> infoList;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textViewInfo = findViewById(R.id.textViewInfo);
        imageView = findViewById(R.id.imageView);
        buttonCeleb = findViewById(R.id.buttonCeleb);
        buttonCeleb.setText("Start");
        counter = 0;

        SourceDownloader task = new SourceDownloader();
        result = null;

        try {

            result = task.execute("https://www.forbes.com/celebrities/#681828fb5947").get();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        celebList = selectNames(result);
        imageList = selectImage(result);
        infoList = selectInfo(result);


    }

    public class SourceDownloader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection connection;

            try {

                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {
                    char current = (char)data;
                    result += current;
                    data = reader.read();
                }

                return result;

            }
            catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    public void clickCeleb(View view) {
        if (counter < celebList.size()) {
            textViewInfo.setText("Rank: " + infoList.get(counter) + " - " + celebList.get(counter));
            Bitmap bitmap = getBitmapFromURL(imageList.get(counter));
            imageView.setImageBitmap(bitmap);

            buttonCeleb.setText("Next");
            counter++;
        } else {
            counter = 0;
            buttonCeleb.setText("Reset?");
        }

        /*for(int i = 0; i < imageList.size(); i++) {
            System.out.println(imageList.get(i));
            System.out.println(i);
        }*/
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);

            return bitmap;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Here's the error");
            return null;
        }
    }

    public List<String> selectNames(String input) {
        String text = input;
        List<String> celebList = new ArrayList<>();

        Pattern p = Pattern.compile("\\{\"title\":\"(.*?)\",\"uri");
        Matcher m = p.matcher(text);

        while (m.find()) {
            celebList.add(m.group(1));
        }

        return celebList;
    }

    public List<String> selectInfo(String input) {
        String text = input;
        List<String> celebList = new ArrayList<>();

        Pattern p = Pattern.compile(",\"rank\":(.*?),");
        Matcher m = p.matcher(text);

        while (m.find()) {
            celebList.add(m.group(1));
        }

        return celebList;
    }

    public List<String> selectImage(String input) {
        String text = input;
        List<String> celebList = new ArrayList<>();

        Pattern p = Pattern.compile(",\"image\":\"//(.*?)back");
        Matcher m = p.matcher(text);

        while (m.find()) {
            celebList.add("https://" + m.group(1));
        }

        return celebList;
    }

}
