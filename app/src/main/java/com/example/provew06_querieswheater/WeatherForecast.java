package com.example.provew06_querieswheater;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WeatherForecast {
    @SerializedName("list")
    private ArrayList<WeatherPack> forecastItems = new ArrayList<WeatherPack>();

    public ArrayList<WeatherPack> getForecastItems() {
        return forecastItems;
    }

    public void setForecastItems(ArrayList<WeatherPack> forecastItems) {
        this.forecastItems = forecastItems;
    }
}
