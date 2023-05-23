package com.example.adsinjava.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adsinjava.MainActivity;
import com.example.adsinjava.R;
import com.example.adsinjava.adapter.ContactAdapter;
import com.example.adsinjava.adapter.ScheduleAdapter;
import com.example.adsinjava.databinding.FragmentContactListBinding;
import com.example.adsinjava.databinding.FragmentScheduleBinding;
import com.example.adsinjava.model.Contact;
import com.example.adsinjava.model.Schedule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContactList extends Fragment {

    FragmentContactListBinding binding;
    FirebaseStorage storage;
    static FirebaseDatabase firebaseDatabase;

    List<Contact> list = new ArrayList<>();

    ContactAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.AddNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_contact_to_fragmentAddNewContact);
            }
        });
        getData();
    }
    void getData() {
        firebaseDatabase.getReference("Contacts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Contact contact = dataSnapshot.getValue(Contact.class);
                    list.add(contact);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("onCancelled: ", error.toString());
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                adapter = new ContactAdapter(list);
                binding.contactListRecycl.setBackgroundColor(Color.parseColor("#D3CFCB"));
                binding.contactListRecycl.setAdapter(adapter);
            }
        }, 2000);
    }
}