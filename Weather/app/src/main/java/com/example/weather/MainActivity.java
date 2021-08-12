package com.example.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.weather.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity<error> extends AppCompatActivity {

    ActivityMainBinding binding;
    private ArrayList<WeatherModel> weatherModelArrayList;
    private CustomAdapter customAdapter;
    int isDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.SearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cityName = binding.CityNameTextVIew.getText().toString();
                Log.d("Search", "clicked");
                getWeatherInfo(cityName);
                binding.textViewCIty.setText(cityName);
            }
        });
        binding.textViewCIty.setText(binding.CityNameTextVIew.getText().toString());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setHasFixedSize(true);
        weatherModelArrayList = new ArrayList<>();
        binding.recyclerView.setAdapter(customAdapter);
        binding.textViewCIty.setText("Vancouver");
        getWeatherInfo("Vancouver");

    }

    public void getWeatherInfo(String cityName){

            String url = "http://api.weatherapi.com/v1/forecast.json?key=4793fcad63794ca1ba4162425210208&q=" + cityName + "&days=1&aqi=no&alerts=no";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject current = response.getJSONObject("current");

                     binding.temperatureTextView.setText(String.format("%s °C", current.getString("temp_c")));
                    int is_day = current.getInt("is_day");

                    Picasso.get().load("http:" + current.getJSONObject("condition").getString("icon")).into(binding.weatherImageView);
                    binding.weatherIcon.setText(current.getJSONObject("condition").getString("text"));

                    if(is_day == 1) {
                        Picasso.get().load("https://i.pinimg.com/originals/61/25/59/6125595537c8fb5fc9c7e6cb256155e9.png").into(binding.backgroundImageView);
                    }
                    else {
                        Picasso.get().load("https://wallpaperaccess.com/full/922698.jpg").into(binding.backgroundImageView);
                    }

                    JSONObject forecast = response.getJSONObject("forecast");
                    JSONObject forecast_Object= forecast.getJSONArray("forecastday").getJSONObject(0);
                    JSONArray hours = forecast_Object.getJSONArray("hour");
                    for (int i = 0; i < hours.length(); i++) {

                        JSONObject hourObj = hours.getJSONObject(0);
                        String time = hourObj.getString("time");
                        String temperature = hourObj.getString("temp_c");
                        String icon ="http:" + hourObj.getJSONObject("condition").getString("icon");
                        String windspeed = hourObj.getString("wind_kph");
                        weatherModelArrayList.add(new WeatherModel(time, temperature, icon, windspeed));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Json", "error");
                Toast.makeText(MainActivity.this, "Please Enter Correct City", Toast.LENGTH_SHORT).show();
            }
        });

        customAdapter = new CustomAdapter(MainActivity.this, weatherModelArrayList);
        binding.recyclerView.setAdapter(customAdapter);
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}