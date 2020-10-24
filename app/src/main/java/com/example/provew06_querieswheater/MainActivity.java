package com.example.provew06_querieswheater;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /** Declaring objects and variables */
    EditText editText;
    Button btnTemp;
    Button btnWForecast;
    ListView showLV;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Assigning value to the objects */
        editText = (EditText) findViewById(R.id.cityName);
        btnTemp = (Button) findViewById(R.id.currentTemp);
        btnWForecast = (Button) findViewById(R.id.weatherForec);
        showLV = (ListView) findViewById(R.id.show);

        /** Requesting the focus to the EditText */
        editText.requestFocus();

        /** Re-writing the OnClickMethod to the btnTemp */
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTemp(v);
            }
        });

        /** Re-writing the OnClickMethod to the btnTemp */
        btnWForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherForecast(v);
            }
        });

        /** Allowing the app to connect to the internet and other things */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    /** Called when the user taps the Current Temperature button */
    public void currentTemp (View view) {
        /** Obtaining the city name */
        String city = editText.getText().toString();

        /** Setting the Runnable and the Thread */
        rCurrentTemp r = new rCurrentTemp(getApplicationContext(), city);
        runOnUiThread(new Thread(r));

        /** Showing a Log message to register the progress */
        Log.i(TAG, "Getting temperature for: " + city);
    }


    /** Called when the user taps the Weather Forecast button */
    public void weatherForecast (View view) {
        /** Obtaining the city name */
        String city = editText.getText().toString();

        /** Setting the Runnable and the Thread */
        rWeatherForecast r = new rWeatherForecast(MainActivity.this, city);
        Thread thread = new Thread (r);
        thread.start();

        /** Showing a Log message to register the progress */
        Log.i(TAG, "Getting weather forecast for: " + city);
    }

    public void fillingListView (ArrayList<String> list) {
        //Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        showLV.setAdapter(adapter);
    }
}