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
import android.widget.Toast;

import com.example.adsinjava.R;
import com.example.adsinjava.adapter.RequestAdapter;
import com.example.adsinjava.databinding.FragmentCreateRequestBinding;
import com.example.adsinjava.databinding.FragmentLoginBinding;
import com.example.adsinjava.model.Requests;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;


public class CreateRequestFragment extends Fragment {

    FragmentCreateRequestBinding binding;

    FirebaseStorage storage;

    static FirebaseDatabase firebaseDatabase;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String key = database.getReference("requests").push().getKey();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateRequestBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonAddNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editTextPhone.getText().toString().matches("") || binding.editTextRequestCreation.getText().toString().matches("")) {
                    Toast.makeText(requireContext(), "Įveskite visus duomenis", Toast.LENGTH_SHORT).show();
                }else
            {
                UploadRequestToDatabase(getRequestDetails());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(), "Sėkmingai išsiųsta", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }}
        });
    }

    private Requests getRequestDetails()
    {
        Requests requests = new Requests(
                binding.editTextRequestCreation.getText().toString(),
                binding.editTextPhone.getText().toString(),
                key
        );
        return requests;
    }

    public static void UploadRequestToDatabase (Requests requests)
    {
        FirebaseDatabase.getInstance().getReference("requests")
                .child(requests.getId())
                .setValue(requests)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // handle success
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("uploadRequestToFirebase: ", e.toString());
                    }
                });
    }
}