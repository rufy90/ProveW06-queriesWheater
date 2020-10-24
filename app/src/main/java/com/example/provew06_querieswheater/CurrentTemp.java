package com.example.provew06_querieswheater;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class CurrentTemp {
    /** The class to obtain the API query values */
    private Integer id;
    @SerializedName("name")
    private String Name;
    @SerializedName("main")
    Map<String, Float> temperature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Map<String, Float> getTemperature() {
        return temperature;
    }

    public void setTemperature(Map<String, Float> temperature) {
        this.temperature = temperature;
    }
}
