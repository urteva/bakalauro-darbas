package com.example.adsinjava.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adsinjava.databinding.ReviewRequestItemBinding;
import com.example.adsinjava.databinding.ScheduleItemBinding;
import com.example.adsinjava.model.Requests;
import com.example.adsinjava.model.Schedule;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.viewholder> {

    private List<Requests> items;

    public RequestAdapter(List<Requests> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewRequestItemBinding binding = ReviewRequestItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class viewholder extends RecyclerView.ViewHolder {
        private ReviewRequestItemBinding binding;

        public viewholder(ReviewRequestItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Requests requests) {
            binding.requestDetails.setText(requests.getRequestText());
            binding.requestAccInfo.setText(requests.getPhone());

            Log.e("bind: ", requests.getRequestText());

        }
    }


}




