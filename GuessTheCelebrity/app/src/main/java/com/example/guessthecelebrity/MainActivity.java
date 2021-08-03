package com.example.guessthecelebrity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static class celebImage extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            URL url;
            String result = "";
            try {
                url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1){
                    char ch = (char) data;
                    result += ch;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebImage celebs = new celebImage();
        String result;
        try {
            result = celebs.execute("https://www.imdb.com/list/ls052283250").get();
            String[] part1 = result.split("<div class=\"desc lister-total-num-results\">");
            String[] part2 = part1[1].split("<!-- begin TOP_RHS -->");

            Pattern p = Pattern.compile("<img alt=\"(.*?)\" height");
            Matcher m = p.matcher(part2[0]);

            while(m.find()){
                System.out.println(m.group(1));
            }

            Pattern p2 = Pattern.compile("src=\"(.*?)\" width=\"140");
            Matcher m2 = p2.matcher(part2[0]);

            while(m2.find()){
                System.out.println(m2.group(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}