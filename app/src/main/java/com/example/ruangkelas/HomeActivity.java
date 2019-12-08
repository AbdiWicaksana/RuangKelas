package com.example.ruangkelas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    List<Classes> listClasses;
    public ClassesAdapter clsAdapter;
    public static final String my_shared_preferences = "my_shared_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        listClasses = new ArrayList<>();
        listClasses.add(new Classes("Kelas Pemrograman Mobile","Kelas Paralel","Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT"));
        listClasses.add(new Classes("Kelas Pemrograman Internet","Kelas Paralel","I Putu Arya Dharmaadi, ST.,MT"));
        listClasses.add(new Classes("Kelas Pemrograman","Kelas Paralel","I Made Sunia Raharja, S.Kom., M.Cs"));
        listClasses.add(new Classes("Kelas Pemrograman Berorientasi Objek","Kelas Paralel","I Putu Arya Dharmaadi, ST.,MT"));
        listClasses.add(new Classes("Kelas Internet of Things","Kelas Paralel","Kadek Suar Wibawa, ST, MT"));
        listClasses.add(new Classes("Kelas Interpesonal Life Skill","Kelas Paralel","Prof. Dr. I Ketut Gede Darma Putra, S.Kom., M.T."));
        listClasses.add(new Classes("Teknologi Basis Data","Kelas Paralel","Dwi Putra Githa, S.T., M.T."));
        listClasses.add(new Classes("Pengolahan Citra Digital","Kelas Paralel","Ni Kadek Ayu Wirdiani, S.T., M.T."));
        listClasses.add(new Classes("Rekayasa Perangkat Lunak","Kelas Paralel","Ni Kadek Dwi Rusjayanthi, S.T., M.T."));
        listClasses.add(new Classes("Aplikasi Sosial Media","Kelas Paralel","I Putu Agus Eka Pratama, S.T., M.T."));

        showClasses();
    }

    private void showClasses() {
        RecyclerView recyclerView = findViewById(R.id.rec_class);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clsAdapter = new ClassesAdapter(this, listClasses);
        recyclerView.setAdapter(clsAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_classes) {
            Intent intentClasses = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intentClasses);
        } else if (id == R.id.nav_contact) {
            Intent intentContact = new Intent(HomeActivity.this, ContactUs.class);
            startActivity(intentContact);

        } else if (id == R.id.nav_report) {
            Intent intentAbout = new Intent(HomeActivity.this, Report.class);
            startActivity(intentAbout);

        } else if (id == R.id.nav_about) {
            Intent intentAbout = new Intent(HomeActivity.this, About.class);
            startActivity(intentAbout);

        } else if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences(my_shared_preferences,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intentLogout = new Intent(HomeActivity.this, Login.class);
            startActivity(intentLogout);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
