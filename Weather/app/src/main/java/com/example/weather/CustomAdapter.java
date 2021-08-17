package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<WeatherModel> weatherModelList;

    public CustomAdapter(Context context, List<WeatherModel> weatherModelList) {
        this.context = context;
        this.weatherModelList = weatherModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WeatherModel weatherModel  = weatherModelList.get(position);
        String time = weatherModel.getTime();
        String[] parts = time.split(" ");
        holder.time_textView.setText(parts[1]);
        holder.temp_textView.setText(String.format("%s °C", weatherModel.getTemperature()));
        holder.wind_textView.setText(String.format("%s km/h", weatherModel.getWindspeed()));
        Picasso.get().load(weatherModelList.get(position).getIcon()).into(holder.cloud_imageView);
    }

    @Override
    public int getItemCount() {
        return weatherModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView time_textView, temp_textView, wind_textView;
        private ImageView cloud_imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time_textView = itemView.findViewById(R.id.timeTextView);
            temp_textView = itemView.findViewById(R.id.tempTextView);
            wind_textView = itemView.findViewById(R.id.windTextView);
            cloud_imageView = itemView.findViewById(R.id.cloudImageView);
        }
    }
}
