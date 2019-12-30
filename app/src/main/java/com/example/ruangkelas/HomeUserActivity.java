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
import android.widget.EditText;

import com.example.ruangkelas.data.Kelas;

import java.util.ArrayList;
import java.util.List;

public class HomeUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        List<Kelas> listClasses;
        public ClassesAdapter clsAdapter;
        EditText clsName;
        EditText clsSubject;
        EditText clsAuthor;
        public static final String my_shared_preferences = "my_shared_preferences";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.app_bar_home_user);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            listClasses = new ArrayList<>();

//            showClasses();
        }

//        private void showClasses() {
//            RecyclerView recyclerView = findViewById(R.id.rec_class);
//            recyclerView.setHasFixedSize(false);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            clsAdapter = new ClassesAdapter(this, listClasses);
//            recyclerView.setAdapter(clsAdapter);
//
//
//        }

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
                Intent intentClasses = new Intent(HomeUserActivity.this, HomeUserActivity.class);
                startActivity(intentClasses);
            } else if (id == R.id.nav_contact) {
                Intent intentContact = new Intent(HomeUserActivity.this, ContactUs.class);
                startActivity(intentContact);

            } else if (id == R.id.nav_report) {
                Intent intentAbout = new Intent(HomeUserActivity.this, Profile.class);
                startActivity(intentAbout);

            } else if (id == R.id.nav_about) {
                Intent intentAbout = new Intent(HomeUserActivity.this, About.class);
                startActivity(intentAbout);

            } else if (id == R.id.nav_logout) {
                SharedPreferences sharedPreferences = getSharedPreferences(my_shared_preferences,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intentLogout = new Intent(HomeUserActivity.this, Login.class);
                startActivity(intentLogout);
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
