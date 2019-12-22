package com.example.ruangkelas;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruangkelas.data.factory.AppDatabase;
import com.example.ruangkelas.data.kelasDAO;
import com.example.ruangkelas.model.kelas;

import java.util.ArrayList;
import java.util.List;

public class HomeActivityAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    List<Classes> listClasses;
    public ClassesAdapter clsAdapter;
    EditText clsName;
    EditText clsSubject;
    EditText clsAuthor;
    public static final String my_shared_preferences = "my_shared_preferences";
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "id12007477_ruangkelas").build();

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

        clsName=(EditText) findViewById(R.id.classname);
        clsSubject=(EditText) findViewById(R.id.classSubject);
        clsAuthor=(EditText) findViewById(R.id.classAuthor);
        Button buttonSave = findViewById(R.id.saveclass);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newClsName=clsName.getText().toString();
                String newClsSubject=clsSubject.getText().toString();
                String newClsAuthor=clsAuthor.getText().toString();

                // add new item to arraylist
                listClasses.add(new Classes("" + newClsName, "" + newClsSubject, "" + newClsAuthor));
                // notify listview of data changed
                clsAdapter.notifyDataSetChanged();
                kelas kls = new kelas();
                kls.setSubjekKelas(newClsSubject);
                kls.setNamaKelas(newClsName);
                kls.setAuthorKelas(newClsAuthor);
                insertData(kls);
                showData();

            }
        });
    }

    private void insertData(final kelas Kelas){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                // melakukan proses insert data
                long status = db.KelasDAO().insertKelas(Kelas);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(HomeActivityAdmin.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void showData (){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                // melakukan proses insert data
                kelasDAO KelasDAO = db.KelasDAO();
                List<kelas> items= KelasDAO.selectAllKelas();
                String test = items.get(2).getNamaKelas();
                return test;
            }

            @Override
            protected void onPostExecute(String test) {
                Toast.makeText(HomeActivityAdmin.this, test, Toast.LENGTH_SHORT).show();
            }
        }.execute();
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
            Intent intentClasses = new Intent(HomeActivityAdmin.this, HomeActivityAdmin.class);
            startActivity(intentClasses);
        } else if (id == R.id.nav_contact) {
            Intent intentContact = new Intent(HomeActivityAdmin.this, ContactUs.class);
            startActivity(intentContact);

        } else if (id == R.id.nav_report) {
            Intent intentAbout = new Intent(HomeActivityAdmin.this, Profile.class);
            startActivity(intentAbout);

        } else if (id == R.id.nav_about) {
            Intent intentAbout = new Intent(HomeActivityAdmin.this, About.class);
            startActivity(intentAbout);

        } else if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences(my_shared_preferences,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intentLogout = new Intent(HomeActivityAdmin.this, Login.class);
            startActivity(intentLogout);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
