package com.example.provew06_querieswheater;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class WeatherPack {
    @SerializedName("main")
    Map<String, Float> measures;
    @SerializedName("wind")
    Map<String, Float> wDescription;

    public Map<String, Float> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, Float> measures) {
        this.measures = measures;
    }

    public Map<String, Float> getwDescription() {
        return wDescription;
    }

    public void setwDescription(Map<String, Float> wDescription) {
        this.wDescription = wDescription;
    }
}
