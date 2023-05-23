package com.example.adsinjava.ui;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.adsinjava.databinding.FragmentTaxCalculatorBinding;
import com.example.adsinjava.model.Schedule;
import com.example.adsinjava.model.Tax;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Calendar;


public class TaxCalculator extends Fragment {

    FragmentTaxCalculatorBinding binding;

    public Double taxx;

    FirebaseStorage storage;

    static FirebaseDatabase firebaseDatabase;
    public Double GautaReiksme;
    SharedPreferences sharedPreferences;


    FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaxCalculatorBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        DatabaseReference valueRef = firebaseDatabase.getReference("Taxes");
        System.out.println(valueRef);



        valueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String key = userSnapshot.getKey(); // key will be "user1" for the first child

                    GautaReiksme = snapshot.child("taxSum").getValue(Double.class);

                    System.out.println(GautaReiksme);

                }

                System.out.println(GautaReiksme);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                System.out.println("nepavyko pasiimt");

            }
        });


//
//
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTax();

        binding.buttonAddTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    setTax();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        binding.buttonDeleteAllTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deleteTax();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }



    void setTax() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                double old = Double.parseDouble(binding.taxSummm.getText().toString());
                double tax = Double.parseDouble(binding.SumInput.getText().toString()) * 0.15;
                double afterTax = Double.parseDouble(binding.SumInput.getText().toString()) - tax;
                double result =  old + tax;
                binding.taxSummm.setText(String.valueOf(result));
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firebaseDatabase.getReference("Taxes").child(sharedPreferences.getString("id", "")).setValue(new Tax(sharedPreferences.getString("id", ""), Double.parseDouble(binding.taxSummm.getText().toString())));

            }
        }, 1000);

    }
    void deleteTax()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                double result =  0;
                binding.taxSummm.setText(String.valueOf(result));
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firebaseDatabase.getReference("Taxes").child(sharedPreferences.getString("id", "")).setValue(new Tax(sharedPreferences.getString("id", ""), Double.parseDouble(binding.taxSummm.getText().toString())));

            }
        }, 1000);

    }

    void getTax() {
        firebaseDatabase.getReference("Taxes").child(sharedPreferences.getString("id", "")).child("taxSum").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    binding.taxSummm.setText(snapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}