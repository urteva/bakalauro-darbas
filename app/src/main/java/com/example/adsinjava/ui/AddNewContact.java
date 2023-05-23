package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adsinjava.R;
import com.example.adsinjava.databinding.FragmentAddNewAdBinding;
import com.example.adsinjava.databinding.FragmentAddNewContactBinding;
import com.example.adsinjava.databinding.FragmentAddNewScheduleBinding;
import com.example.adsinjava.model.Contact;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Calendar;

public class AddNewContact extends Fragment {

    FragmentAddNewContactBinding binding;
    FirebaseStorage storage;
    static FirebaseDatabase firebaseDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewContactBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.InputPhone.getText() != null && binding.Pavadinimas.getText() != null) {
                      uploadContactToFirebase(getContactDetails());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigate(R.id.contactsFragment);
                        }
                    }, 1000);
                } else
                    Toast.makeText(requireContext(), "Įveskite trūkstamus duomenis", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Contact getContactDetails(){
        Contact contact = new Contact(
                binding.InputPhone.getText().toString(),
                FirebaseAuth.getInstance().getUid(),
                binding.Pavadinimas.getText().toString(),
                binding.OtherDetails.getText().toString()
        );
        return contact;
    }

    public static void uploadContactToFirebase(Contact contact){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return;
        }
        contact.setId(currentUser.getUid() + Calendar.getInstance().getTimeInMillis());
        FirebaseDatabase.getInstance().getReference("Contacts")
                .child(contact.getId())
                .setValue(contact)
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