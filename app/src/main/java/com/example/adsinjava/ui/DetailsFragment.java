package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.adsinjava.R;
import com.example.adsinjava.databinding.FragmentDetailsBinding;
import com.example.adsinjava.model.Ads;


public class DetailsFragment extends Fragment {

   FragmentDetailsBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       Ads args = DetailsFragmentArgs.fromBundle(getArguments()).getDetails();

        binding.txtDesc.setText(args.getDescription());
        binding.txtPrice.setText(args.getPrice());
        binding.txtPhone.setText(args.getPhone());
        binding.txtAddress.setText(args.getAddress());
        Glide.with(requireContext()).load(args.getImgURL()).into(binding.imgAds);

    }
}