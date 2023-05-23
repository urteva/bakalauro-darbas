package com.example.adsinjava.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adsinjava.databinding.AdItemBinding;
import com.example.adsinjava.databinding.ScheduleItemBinding;
import com.example.adsinjava.model.Ads;
import com.example.adsinjava.model.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.viewholder> {

    private List<Schedule> items;

    public ScheduleAdapter(List<Schedule> items) {
        this.items = items;
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        private ScheduleItemBinding binding;

        public viewholder(ScheduleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Schedule data) {

            binding.scheduleItemDate.setText(data.getDate());
            binding.scheduleItemType.setText(data.getScheduleType());
            binding.scheduleItemDetails.setText(data.getScheduleDetails());
            binding.Scheduletime.setText(data.getTime());
            Log.e("bind: ", data.getScheduleType());


        }
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        ScheduleItemBinding binding = ScheduleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
