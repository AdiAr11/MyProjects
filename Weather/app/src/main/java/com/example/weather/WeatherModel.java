package com.example.weather;

public class WeatherModel {

    private String time;
    private String temperature;
    private String icon;
    private String windspeed;

    public WeatherModel(String time, String temperature, String icon, String windspeed) {
        this.time = time;
        this.temperature = temperature;
        this.icon = icon;
        this.windspeed = windspeed;
    }

    public String getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public String getWindspeed() {
        return windspeed;
    }

}
