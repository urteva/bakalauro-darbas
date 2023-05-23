package com.example.adsinjava.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adsinjava.databinding.AdItemBinding;
import com.example.adsinjava.databinding.DocumentItemBinding;
import com.example.adsinjava.model.Ads;
import com.example.adsinjava.ui.DocumentsDirections;

import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.viewholder> {

    private List<String> items;

    public PdfAdapter(List<String> items) {
        this.items = items;
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        private DocumentItemBinding binding;

        public viewholder(DocumentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind( int position, List<String> items) {
            binding.tvTitle.setText(items.get(position));
            Log.e( "bind: ", binding.tvTitle.getText().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DocumentsDirections.ActionFragmentDocumentToPdfFragment action =
                            DocumentsDirections.actionFragmentDocumentToPdfFragment(items.get(position));
                    Navigation.findNavController(view).navigate(action);
                }
            });
        }
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        DocumentItemBinding binding = DocumentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        holder.bind(position,items);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
