package com.example.adsinjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.adsinjava.databinding.ActivityMainBinding;
import com.example.adsinjava.ui.TaxCalculator;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#D3CFCB"));
        actionBar.setBackgroundDrawable(colorDrawable);


        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout,
                R.string.nav_open,
                R.string.nav_close
        );

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(binding.drawerLayout)
                .build();


        NavigationUI.setupWithNavController(binding.navViewBot, navController);
        binding.navView.setNavigationItemSelectedListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            binding.navViewBot.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.nav_contacts:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_home).navigate(R.id.contactsFragment);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_SeeRequests:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_home).navigate(R.id.reviewRequestsFragment);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_Mokesciai:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_home).navigate(R.id.taxCalculatorFragment);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_schedule:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_home).navigate(R.id.scheduleFragment);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_documents:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_home).navigate(R.id.fragmentDocument);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            default:
                return false;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

//            binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
//            actionBarDrawerToggle.syncState();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}