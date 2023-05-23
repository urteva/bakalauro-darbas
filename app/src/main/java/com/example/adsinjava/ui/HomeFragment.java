package com.example.adsinjava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adsinjava.R;
import com.example.adsinjava.adapter.AdAdapter;
import com.example.adsinjava.databinding.FragmentHomeBinding;
import com.example.adsinjava.model.Ads;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    FirebaseDatabase firebaseDatabase;
    AdAdapter adapter;
    List<Ads> list = new ArrayList<>();
    List<Ads> filteredList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        firebaseDatabase = FirebaseDatabase.getInstance();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            setHasOptionsMenu(true);

        } else {
            binding.radioOnlyMyEyes.setVisibility(View.GONE);
        }
        list.clear();
        filteredList.clear();
        getAllAds();


        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (getSelectedType().equals("Tik man")) {

                    List<Ads> filterList = filteredList.stream().filter(s -> s.getUserId().equals(FirebaseAuth.getInstance().getUid())&&s.getType().equals("Tik man")).collect(Collectors.toList());
                    adapter = new AdAdapter(filterList);
                    binding.recycleAds.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Log.e("onViewCreated: ", getSelectedType());


                } else {
                    List<Ads> filterList = filteredList.stream().filter(s -> s.getType().equals(getSelectedType())).collect(Collectors.toList());
                    adapter = new AdAdapter(filterList);
                    binding.recycleAds.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    Log.e("onViewCreated: ", getSelectedType());
                }
            }
        });


    }

    public String getSelectedType() {
        int radioButtonID = binding.radioGroup.getCheckedRadioButtonId();
        View radioButton = binding.radioGroup.findViewById(radioButtonID);
        int idx = binding.radioGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton) binding.radioGroup.getChildAt(idx);
        String selectedtext = r.getText().toString();
        return selectedtext;
    }

    void getAllAds() {
        firebaseDatabase.getReference("ads").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                Ads ad = new Ads();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ad  = dataSnapshot.getValue(Ads.class);
                         list.add(ad);
                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("onCancelled: ", error.getDetails());
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Ads> filterList = list.stream().filter(s -> s.getType().equals("Nuoma")||s.getType().equals("Parduodama")).collect(Collectors.toList());
                adapter = new AdAdapter(filterList);
                filteredList.addAll(list);
                binding.recycleAds.setLayoutManager(new LinearLayoutManager(requireContext()));
                binding.recycleAds.setAdapter(adapter);
            }
        }, 2000);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.optional_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNew:
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_home)
                        .navigate(R.id.action_nav_home_to_addNewAdFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e( "onResume: ", "resume");
        binding.radioForRent.setChecked(false);
        binding.radioOnlyMyEyes.setChecked(false);
        binding.radioToBuy.setChecked(false);
    }
}