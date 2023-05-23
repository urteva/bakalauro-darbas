package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.adsinjava.R;
import com.example.adsinjava.adapter.PdfAdapter;
import com.example.adsinjava.constants.Constants;
import com.example.adsinjava.databinding.FragmentDocumentsBinding;
import com.example.adsinjava.databinding.FragmentScheduleBinding;

import java.util.ArrayList;
import java.util.List;

public class Documents extends Fragment {

    PdfAdapter adapter;
    FragmentDocumentsBinding binding;

    List<String> pdfDocuments = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDocumentsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


            pdfDocuments.clear();

            pdfDocuments.add(Constants.pdf1);
            pdfDocuments.add(Constants.pdf2);
            pdfDocuments.add(Constants.pdf3);
            pdfDocuments.add(Constants.pdf4);

        adapter = new PdfAdapter(pdfDocuments);
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.documentList.setLayoutManager(linearLayoutManager);

        binding.documentList.setAdapter(adapter);


    }
}