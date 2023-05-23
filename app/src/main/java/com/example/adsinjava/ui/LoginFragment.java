package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adsinjava.R;
import com.example.adsinjava.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    FirebaseAuth firebaseAuth ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        firebaseAuth = FirebaseAuth.getInstance();
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editUserName.getText().toString().matches("") ||  binding.editPassword.getText().toString().matches(""))
                {
                    Toast.makeText(requireContext(), "Įveskite duomenis", Toast.LENGTH_SHORT).show();
                }
                else
               loginUser(binding.editUserName.getText().toString(), binding.editPassword.getText().toString());
            }
        });
    }

    public void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Sėkmingai prisijungta", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
                startActivity(requireActivity().getIntent());
            } else {
                Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}