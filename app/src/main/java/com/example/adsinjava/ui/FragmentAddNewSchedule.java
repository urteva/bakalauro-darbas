package com.example.adsinjava.ui;

import android.graphics.Color;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.adsinjava.R;
import com.example.adsinjava.databinding.FragmentAddNewAdBinding;
import com.example.adsinjava.databinding.FragmentAddNewScheduleBinding;
import com.example.adsinjava.databinding.FragmentReviewRequestsBinding;
import com.example.adsinjava.model.Ads;
import com.example.adsinjava.model.Schedule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentAddNewSchedule extends Fragment {

    FragmentAddNewScheduleBinding binding;

    FirebaseStorage storage;
    static FirebaseDatabase firebaseDatabase;

    public String date;

    public String spinnerPickedItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewScheduleBinding.inflate(getLayoutInflater());
        // firebaseAuth = FirebaseAuth.getInstance();


        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        spinnerDetails();
        calendarDetails();


        return binding.getRoot();


    }

    private void calendarDetails() {
        binding.calendarPickDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1 = i1+1;
                date = i + "/" + i1 + "/" + i2;

            }
        });

          }

    private void spinnerDetails() {
        binding.spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerPickedItem = binding.spinnerItem.getItemAtPosition(i).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.saveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.calendarPickDate!=null && binding.spinnerItem!= null){
                    uploadScheduleToFirebase(getScheduleDetails());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigate(R.id.scheduleFragment);
                        }
                    }, 1000);
                }
                else {
                    Toast.makeText(requireContext(), "Įveskite trūkstamus duomenis", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private Schedule getScheduleDetails() {
        Schedule schedule = new Schedule(
                date,
                spinnerPickedItem,
                binding.editTextItem.getText().toString(),
               " " + binding.editTextTime.getText().toString() + ":" + binding.minutes.getText().toString(),
                FirebaseAuth.getInstance().getUid()
        );
        return schedule;
    }

    public static void uploadScheduleToFirebase(Schedule schedule) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return;
        }
        schedule.setId(currentUser.getUid() + Calendar.getInstance().getTimeInMillis());

        FirebaseDatabase.getInstance().getReference("Schedule")
                .child(schedule.getId())
                .setValue(schedule)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // handle success
                        System.out.println("sekmingai atsirado firebase");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("neaitsirado firebase");
                    }
                });

    }
}