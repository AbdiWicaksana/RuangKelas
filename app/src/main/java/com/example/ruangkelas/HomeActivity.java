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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ruangkelas.app.AppController;
import com.example.ruangkelas.data.factory.AppDatabase;
import com.example.ruangkelas.model.kelas;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    List<kelas> listKelas;
    public ClassesAdapter clsAdapter;
    public static final String my_shared_preferences = "my_shared_preferences";
    private AppDatabase db;

    TextView txt_nama_header, txt_email_header;
    ImageView photo_profile;
    SharedPreferences sharedpreferences;
    String id, nama, email;
    int success;

    private static final String TAG = HomeActivity.class.getSimpleName();

    private String SELECT_URL = Server.URL + "select_photo.php";

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_EMAIL = "email";
    private static final String TAG_PHOTO = "photo";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_nama_header = headerView.findViewById(R.id.txt_nama_header);
        txt_email_header = headerView.findViewById(R.id.txt_email_header);
        photo_profile = headerView.findViewById(R.id.photo_profile);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        email = sharedpreferences.getString(TAG_EMAIL, null);

        txt_nama_header.setText(nama);
        txt_email_header.setText(email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "id12007477_ruangkelas").allowMainThreadQueries().build();

        navigationView.setNavigationItemSelectedListener(this);
        listKelas = new ArrayList<>();

        listKelas.addAll(Arrays.asList(db.KelasDAO().readDataKelas()));

        showClasses();

        StringRequest strReq = new StringRequest(Request.Method.POST, SELECT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response" + response.toString());

                try {
//                    Toast.makeText(getActivity(), user_id, Toast.LENGTH_LONG).show();
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.d("get photo profile", jObj.toString());
                        String id = (jObj.getString(TAG_ID));
                        String photo = (jObj.getString(TAG_PHOTO));

                        if (!id.isEmpty()) {
                            Picasso.with(HomeActivity.this).load(photo).centerCrop().fit().into(photo_profile);

                        } else {
                            Toast.makeText(HomeActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(HomeActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showClasses() {
        RecyclerView recyclerView = findViewById(R.id.rec_class);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clsAdapter = new ClassesAdapter(this, listKelas);
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
            Intent intentAbout = new Intent(HomeActivity.this, Profile.class);
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
