package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterDay extends RecyclerView.Adapter<CustomAdapterDay.ViewHolder> {

    private List<WeatherModel> weatherModelList;

    public CustomAdapterDay(List<WeatherModel> weatherModelList) {
        this.weatherModelList = weatherModelList;
    }

    @NonNull
    @Override
    public CustomAdapterDay.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterDay.ViewHolder holder, int position) {

        WeatherModel weatherModel = weatherModelList.get(position);
        String str = weatherModel.getTime();
        String[] parts = str.split(" ");
        holder.timeTextView.setText(parts[1]);
        holder.tempTextView.setText(String.format("%s°C", weatherModel.getTemperature()));
        holder.windSpeedTextView.setText(String.format("%skm/h", weatherModel.getWindspeed()));
        Picasso.get().load(weatherModel.getIcon()).into(holder.cloudImageView);
    }

    @Override
    public int getItemCount() {
        return weatherModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView timeTextView, tempTextView, windSpeedTextView;
        private final ImageView cloudImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            timeTextView = itemView.findViewById(R.id.timeTextViewDay);
            tempTextView = itemView.findViewById(R.id.tempTextViewDay);
            windSpeedTextView = itemView.findViewById(R.id.windTextViewDay);
            cloudImageView = itemView.findViewById(R.id.cloudImageViewDay);
        }
    }
}
