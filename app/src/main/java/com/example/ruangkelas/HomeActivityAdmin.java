package com.example.ruangkelas;

import android.app.ProgressDialog;
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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ruangkelas.app.AppController;
import com.example.ruangkelas.data.Kelas;
import com.example.ruangkelas.data.factory.AppDatabase;
import com.example.ruangkelas.data.kelasDAO;
import com.example.ruangkelas.model.kelas;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivityAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView kList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Kelas> kelasList;
    private RecyclerView.Adapter adapter;

    List<kelas> listKelas;
    public ClassesAdapter clsAdapter;
    EditText clsName, clsSubject, clsAuthor;
    ProgressDialog pDialog;
    TextView txt_nama_header, txt_email_header;
    ImageView photo_profile;
    int success;
    Intent intent;
    String id, nama, email;
    SharedPreferences sharedPreferences;

    public static final String my_shared_preferences = "my_shared_preferences";
    private AppDatabase db;

    private static final String url_add = Server.URL + "add_kelas.php";
    private static final String url_get = Server.URL + "get_kelas.php";
    private String SELECT_URL = Server.URL + "select_photo.php";
    private static final String TAG = HomeActivityAdmin.class.getSimpleName();

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
        setContentView(R.layout.activity_home_admin);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_nama_header = headerView.findViewById(R.id.txt_nama_header);
        txt_email_header = headerView.findViewById(R.id.txt_email_header);
        photo_profile = headerView.findViewById(R.id.photo_profile);

        sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(TAG_ID, null);
        nama = sharedPreferences.getString(TAG_NAMA, null);
        email = sharedPreferences.getString(TAG_EMAIL, null);

        txt_nama_header.setText(nama);
        txt_email_header.setText(email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        kList = findViewById(R.id.rec_class);

        kelasList = new ArrayList<>();
        adapter = new KelasAdapter(getApplicationContext(),kelasList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(kList.getContext(), linearLayoutManager.getOrientation());

        kList.setHasFixedSize(true);
        kList.setLayoutManager(linearLayoutManager);
        kList.addItemDecoration(dividerItemDecoration);
        kList.setAdapter(adapter);

        clsName=(EditText) findViewById(R.id.classname);
        clsSubject=(EditText) findViewById(R.id.classSubject);
        Button buttonSave = findViewById(R.id.saveclass);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "id12007477_ruangkelas").allowMainThreadQueries().build();

        navigationView.setNavigationItemSelectedListener(this);
        listKelas = new ArrayList<>();

        listKelas.addAll(Arrays.asList(db.KelasDAO().readDataKelas()));

        getData();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
                id = sharedPreferences.getString(TAG_ID, null);
                String nama_kelas=clsName.getText().toString();
                String subject_kelas=clsSubject.getText().toString();

                addKelas(id, nama_kelas, subject_kelas);

            }
        });

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
                            Picasso.get().load(photo).centerCrop().fit().into(photo_profile);

                        } else {
                            Toast.makeText(HomeActivityAdmin.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(HomeActivityAdmin.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(HomeActivityAdmin.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

//        showClasses();
    }

//    private void showClasses() {
//        RecyclerView recyclerView = findViewById(R.id.rec_class);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        clsAdapter = new ClassesAdapter(this, listKelas);
//        recyclerView.setAdapter(clsAdapter);
//
//        clsName=(EditText) findViewById(R.id.classname);
//        clsSubject=(EditText) findViewById(R.id.classSubject);
////        clsAuthor=(EditText) findViewById(R.id.classAuthor);
//        Button buttonSave = findViewById(R.id.saveclass);
//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newClsName=clsName.getText().toString();
//                String newClsSubject=clsSubject.getText().toString();
////                String newClsAuthor=clsAuthor.getText().toString();
//
////                kelas kls = new kelas();
////                kls.setSubjekKelas(newClsSubject);
////                kls.setNamaKelas(newClsName);
////                kls.setAuthorKelas(newClsAuthor);
////                insertData(kls);
////                clsAdapter.notifyDataSetChanged();
//
//
//            }
//        });
//    }

//    private void insertData(final kelas Kelas){
//
//        new AsyncTask<Void, Void, Long>(){
//            @Override
//            protected Long doInBackground(Void... voids) {
//                // melakukan proses insert data
//                long status = db.KelasDAO().insertKelas(Kelas);
//                return status;
//            }
//
//            @Override
//            protected void onPostExecute(Long status) {
//                Toast.makeText(HomeActivityAdmin.this, "status row "+status, Toast.LENGTH_SHORT).show();
//            }
//        }.execute();
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void addKelas(final String id, final String nama_kelas, final String subject_kelas) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_add, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Daftar Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Daftar!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        clsName.setText("");
                        clsSubject.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("nama_kelas", nama_kelas);
                params.put("subject_kelas", subject_kelas);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url_get, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Kelas kelas = new Kelas();
                        kelas.setId(jsonObject.getInt("id"));
                        kelas.setNama_kelas(jsonObject.getString("nama_kelas"));
                        kelas.setSubject_kelas(jsonObject.getString("subject_kelas"));
                        kelas.setAuthor_kelas(jsonObject.getString("author_kelas"));

                        kelasList.add(kelas);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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
