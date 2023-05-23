package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adsinjava.R;
import com.example.adsinjava.constants.Constants;
import com.example.adsinjava.databinding.FragmentPdfBinding;
import com.example.adsinjava.model.Ads;


public class PdfFragment extends Fragment {



    FragmentPdfBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     binding = FragmentPdfBinding.inflate(inflater, container, false);
     return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String args = PdfFragmentArgs.fromBundle(getArguments()).getPdf();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(args);

        switch (args){
        case Constants.pdf1:{
            binding.pdfView.fromAsset("dvisale_sutartis.pdf").load();
        } break;

            case Constants.pdf2:{
                binding.pdfView.fromAsset("preliminarioji.pdf").load();
            } break;
            case Constants.pdf3:{
                binding.pdfView.fromAsset("tarpininkavimo.pdf").load();
            } break;
            case Constants.pdf4:{
                binding.pdfView.fromAsset("zemes_ukio_deklaracija.pdf").load();

            } break;

        }



    }
}