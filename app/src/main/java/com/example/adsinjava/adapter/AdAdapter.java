package com.example.adsinjava.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adsinjava.databinding.AdItemBinding;
import com.example.adsinjava.model.Ads;
import com.example.adsinjava.ui.HomeFragmentDirections;


import java.util.List;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.viewholder> {

    private List<Ads> items;

    public AdAdapter(List<Ads> items) {
        this.items = items;
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        private AdItemBinding binding;

        public viewholder(AdItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Ads data, int position, List<Ads> items) {
            Glide.with(binding.getRoot().getContext()).load(data.getImgURL()).into(binding.adImage);
            binding.txtDesc.setText(data.getDescription());
            binding.txtPrice.setText(data.getPrice());

            Log.e("bind12: ", data.getDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    com.example.adsinjava.ui.HomeFragmentDirections.ActionNavHomeToDetailsFragment action =
                            com.example.adsinjava.ui.HomeFragmentDirections.actionNavHomeToDetailsFragment(items.get(position));
                    Navigation.findNavController(view).navigate(action);
                }
            });
        }
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdItemBinding binding = AdItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        holder.bind(items.get(position),position,items);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
