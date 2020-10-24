package com.example.provew06_querieswheater;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class rCurrentTemp implements Runnable {
    /** Declaring objects and variables */
    private static final String TAG = "MyActivity";
    private final WeakReference<Context> contextRef;
    private final String city;
    private static final String url = "https://api.openweathermap.org/data/2.5/weather";
    private static final String key = "7e907f22e967c3a05000cd2f33087775";
    private static final String charset = "UTF-8";

    public rCurrentTemp(Context context, String city) {
        /** Declaring the constructor values */
        this.contextRef = new WeakReference<Context>(context);
        this.city = city;
    }

    @Override
    public void run() {
        try {
            Context context = contextRef.get();
            if (context != null) {
                Toast.makeText(context, "Starting query!", Toast.LENGTH_SHORT).show();

            }

            /** Setting the query to the API */
            String query = String.format("?q=%s&units=metric&apiKey=%s",
                    URLEncoder.encode(city, charset),
                    URLEncoder.encode(key, charset));
            URLConnection connection = new URL(url + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            /** Passing the obtained query to the Gson */
            Gson gson = new Gson();
            CurrentTemp conditions = gson.fromJson(stringBuilder.toString(), CurrentTemp.class);
            Log.i(TAG, "Showing temperature from: " + city);

            /** Passing the context to the Toast message */
            context = contextRef.get();
            if (context != null) {
                Toast.makeText(context, "Current Temperature: " + conditions.temperature.get("temp") + " in: " + city, Toast.LENGTH_SHORT).show();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
