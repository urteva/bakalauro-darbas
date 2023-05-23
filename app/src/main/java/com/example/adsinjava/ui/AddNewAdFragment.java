package com.example.adsinjava.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adsinjava.R;
import com.example.adsinjava.databinding.FragmentAddNewAdBinding;
import com.example.adsinjava.model.Ads;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.UUID;


public class AddNewAdFragment extends Fragment {

   FragmentAddNewAdBinding binding;
   FirebaseStorage storage;
   static FirebaseDatabase firebaseDatabase;
   Uri imgUri;
    String selectedtext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewAdBinding.inflate(getLayoutInflater());
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getImagePicked();
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextDescription.getText().toString()!=""&&
                binding.editTextPhone.getText().toString()!=""){
                    uploadAdToFirebase(getAdDetails(),imgUri);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(view).navigate(R.id.nav_home);
                        }
                    },1000);

                } else {
                    Toast.makeText(requireContext(), "iveskite visus duomenis", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getImagePicked() {
        ActivityResultLauncher<String> loadFile2 = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri it) {
                        if (it != null) {
                            imgUri = it;
                            Glide.with(requireContext()).asBitmap().load(it).into(binding.imageAd);
                            binding.viewUploadImage.setVisibility(View.GONE);
                        }
                    }
                });

        binding.viewUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFile2.launch("image/*");
            }
        });
    }

    public String getSelectedType() {
        int radioButtonID = binding.radioGroup.getCheckedRadioButtonId();
        View radioButton = binding.radioGroup.findViewById(radioButtonID);
        int idx = binding.radioGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton) binding.radioGroup.getChildAt(idx);
         selectedtext = r.getText().toString();
        return selectedtext;
    }

    private Ads getAdDetails() {
        Ads ad = new Ads(
                binding.editTextDescription.getText().toString(),
                binding.editTextPhone.getText().toString(),
                binding.editTextPrice.getText().toString()+"€",
                binding.editTextAddress.getText().toString(),
                getSelectedType(),
                FirebaseAuth.getInstance().getUid()
        );
        return ad;
    }

    private static void uploadImg(Uri imgUri, Ads ad) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/" + UUID.randomUUID().toString());
        storageRef.putFile(imgUri).addOnSuccessListener(taskSnapshot -> {
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                firebaseDatabase.getReference("ads")
                        .child(ad.getId())
                        .child("imgURL").setValue(uri.toString());

            });
        });

    }

    public static void uploadAdToFirebase(Ads ad, Uri imgUri) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return;
        }
        ad.setId(currentUser.getUid() + Calendar.getInstance().getTimeInMillis());

        FirebaseDatabase.getInstance().getReference("ads")
                .child(ad.getId())
                .setValue(ad)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // handle success
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("uploadAdToFirebase: ", e.toString());
                    }
                });

        uploadImg(imgUri, ad);
    }









}