package com.example.provew06_querieswheater;

import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class rWeatherForecast implements Runnable {
    /** Declaring objects and variables */
    private static final String TAG = "MyActivity";
    private final WeakReference<MainActivity> activitytRef;
    private final String city;
    private static final String url = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String key = "7e907f22e967c3a05000cd2f33087775";
    private static final String charset = "UTF-8";

    public rWeatherForecast(MainActivity activity, String city) {
        /** Declaring the constructor values */
        this.activitytRef = new WeakReference<MainActivity>(activity);
        this.city = city;
    }

    @Override
    public void run() {
        MainActivity activity = activitytRef.get();
        if (activity != null) {
            //Toast.makeText(activity, "Starting query!", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
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
            WeatherForecast weatherFiveDays = gson.fromJson(stringBuilder.toString(), WeatherForecast.class);

            /** Passing the context to the Toast message */
            activity = activitytRef.get();
            if (activity != null) {
                //Toast.makeText(activity, "Query successfully obtained!", Toast.LENGTH_SHORT).show();
            }

            Log.i(TAG, "Passing the data Forecast to the List View");

            /** Passing the data to the listView */
            ArrayList<WeatherPack> arrayList = weatherFiveDays.getForecastItems();
            ArrayList<String> dataList = new ArrayList<String>();


            for (int i=0;i<arrayList.size();i++) {
                WeatherPack weather = arrayList.get(i);
                dataList.add("Temperature: " + weather.measures.get("temp") + " -|- " + "Wind Speed:" + weather.wDescription.get("speed"));
                //System.out.println("Temperature: \t Speed:");
                //System.out.println(weather.measures.get("temp") + "\t\t\t" + weather.wDescription.get("speed"));
            }

            MainActivity finalActivity = activity;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(dataList.size() > 0){
                        finalActivity.fillingListView(dataList);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
