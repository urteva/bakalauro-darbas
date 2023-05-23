package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adsinjava.R;
import com.example.adsinjava.adapter.RequestAdapter;
import com.example.adsinjava.adapter.ScheduleAdapter;
import com.example.adsinjava.databinding.FragmentCreateRequestBinding;
import com.example.adsinjava.databinding.FragmentReviewRequestsBinding;
import com.example.adsinjava.model.Ads;
import com.example.adsinjava.model.Requests;
import com.example.adsinjava.model.Schedule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ReviewRequestsFragment extends Fragment {

    FragmentReviewRequestsBinding binding;

    List<Requests> list = new ArrayList<>();

    RequestAdapter adapter;

    FirebaseStorage storage;
    static FirebaseDatabase firebaseDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReviewRequestsBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }
    void getData() {
        firebaseDatabase.getReference("requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Requests requests = dataSnapshot.getValue(Requests.class);
                    list.add(requests);

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


                adapter = new RequestAdapter(list);
                binding.reviewRequest.setAdapter(adapter);
            }
        }, 2000);
    }



}