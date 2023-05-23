package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adsinjava.R;
import com.example.adsinjava.adapter.ScheduleAdapter;
import com.example.adsinjava.databinding.FragmentAddNewAdBinding;
import com.example.adsinjava.databinding.FragmentScheduleBinding;
import com.example.adsinjava.model.Ads;
import com.example.adsinjava.model.Schedule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleFragment extends Fragment {

    FragmentScheduleBinding binding;
    FirebaseStorage storage;
    static FirebaseDatabase firebaseDatabase;
    List<Schedule> list = new ArrayList<>();
    List<Schedule> sortedlist = new ArrayList<>();
    ScheduleAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.AddNewScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_scheduleFragment_to_fragmentAddNewSchedule);

            }
        });
        getData();
    }


    void getData() {
        firebaseDatabase.getReference("Schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Schedule schedule = dataSnapshot.getValue(Schedule.class);
                    list.add(schedule);
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
                Comparator<Schedule> dateComparator = Comparator.comparing(Schedule::getDate).reversed();
                list.sort(dateComparator);

                adapter = new ScheduleAdapter(list);
                binding.RecyclerViewSchedule.setAdapter(adapter);
            }
        }, 2000);
    }
}