package com.example.adsinjava.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adsinjava.databinding.ContactItemBinding;
import com.example.adsinjava.databinding.ScheduleItemBinding;
import com.example.adsinjava.model.Contact;
import com.example.adsinjava.model.Schedule;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewholder> {

    private List<Contact> items;
    public ContactAdapter(List<Contact> items) {
        this.items = items;
    }


   public static class viewholder extends RecyclerView.ViewHolder{
        private ContactItemBinding binding;

        public viewholder (ContactItemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Contact data)
        {
            binding.contactName.setText(data.getContactDetails());
            binding.otherContactDetails.setText(data.getOtherDetails());
            binding.phoneNumber.setText(data.getPhoneNumb());

        }
   }
    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactItemBinding binding = ContactItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
