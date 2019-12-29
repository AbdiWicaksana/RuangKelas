package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ruangkelas.app.AppController;
import com.example.ruangkelas.data.Assignment;
import com.example.ruangkelas.data.Timeline;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssigmentFragment extends Fragment {
    View v2;
//    RecyclerView recyclerView;
//    List<Assigment> listAssigment;
//    private AssigmentAdapter assignAdapter;
    EditText editTextNewAssign;
    EditText editTextNewDateAssign;
    EditText editTextNewDtlAssign;

    ProgressDialog pDialog;
    String id_user;
    SharedPreferences sharedpreferences;
    int success;

    private RecyclerView aList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Assignment> assignmentList;
    private RecyclerView.Adapter adapter;


    private static final String url_add = Server.URL + "add_assignment.php";
    private static final String url_get = Server.URL + "get_assignment.php";
    private static final String TAG = HomeActivityAdmin.class.getSimpleName();

    public static final String TAG_ID = "id";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public AssigmentFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v2 = inflater.inflate(R.layout.assignment_layout,container,false);
//        recyclerView = (RecyclerView) v2.findViewById(R.id.rec_assigment);
//        assignAdapter = new AssigmentAdapter(getContext(), listAssigment);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(assignAdapter);
        aList = v2.findViewById(R.id.rec_assigment);

        assignmentList = new ArrayList<>();
        adapter = new AssigmentAdapter(getActivity(),assignmentList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(aList.getContext(), linearLayoutManager.getOrientation());

        aList.setHasFixedSize(true);
        aList.setLayoutManager(linearLayoutManager);
        aList.addItemDecoration(dividerItemDecoration);
        aList.setAdapter(adapter);

        editTextNewAssign=(EditText) v2.findViewById(R.id.namaTugas);
        editTextNewDateAssign=(EditText) v2.findViewById(R.id.tanggalTugas);
        editTextNewDtlAssign=(EditText) v2.findViewById(R.id.detailTugas);
        Button btAddAssign=(Button) v2.findViewById(R.id.saveassignment);

        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id_user = sharedpreferences.getString(TAG_ID, null);

        final String id_kelas = getActivity().getIntent().getStringExtra("id_kelas");

        getData(id_kelas);

        Toast.makeText(getActivity(), id_kelas, Toast.LENGTH_LONG).show();

        btAddAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAssign=editTextNewAssign.getText().toString();
                String newDtlAssign=editTextNewDtlAssign.getText().toString();
                String newDateAssign=editTextNewDateAssign.getText().toString();

                Toast.makeText(getActivity(), id_user, Toast.LENGTH_LONG).show();

                addAssignment(id_kelas, newAssign, newDtlAssign, newDateAssign);
//                // add new item to arraylist
//                listAssigment.add(new Assigment("" + newAssign,"" + newDateAssign,"https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","" + newDtlAssign));
//                // notify listview of data changed
//                assignAdapter.notifyDataSetChanged();
            }
        });

        TextView buttonBckAssign = v2.findViewById(R.id.bckAssign);
        buttonBckAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v2;
    }

    private void addAssignment(final String id_kelas, final String newAssign, final String newDtlAssign, final String newDateAssign) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
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

                        Toast.makeText(getActivity().getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

//                        editTextNewNIM.setText("");

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
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
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_kelas", id_kelas);
                params.put("nama_assignment", newAssign);
                params.put("detail_assignment", newDtlAssign);
                params.put("date_assignment", newDateAssign);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void getData(final String id_kelas) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url_get + "?id_kelas=" + id_kelas, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Assignment assignment = new Assignment();
                        assignment.setId(jsonObject.getInt("id"));
                        assignment.setNama_assignment(jsonObject.getString("nama_assignment"));
                        assignment.setDetail_assignment(jsonObject.getString("detail_assignment"));
                        assignment.setDate_assignment(jsonObject.getString("date_assignment"));

                        assignmentList.add(assignment);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        pDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                pDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        listAssigment = new ArrayList<>();
//        listAssigment.add(new Assigment("Tugas Pertemuan 1","01 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program Hello World sederhana, tugas dikumpul ke email saya dengan kode 6YH-123-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 2","08 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah Layout pada Android Studio menggunakan Relative Layout, tugas dikumpul ke email saya dengan kode 6YH-124-NIM"));
//        listAssigment.add(new Assigment("Tugas Tambahan","15 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah Desain pada Android Studio yang mengimplementasikan Relative Layout, tugas dikumpul ke email saya dengan kode 6YH-125-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 3","22 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program sederhana yang mengimplementasikan pengguanaan Constraint Layout, tugas dikumpul ke email saya dengan kode 6YH-126-NIM"));
//        listAssigment.add(new Assigment("Tugas Pengganti UTS","2 April 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program dengan menggunakan RecyclerView dengan tampilan mode grid, list, card view, tugas dikumpul ke email saya dengan kode 6YH-127-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 5","9 April 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program dengan menggunakan dua recycler view dalam satu page /layout, tugas dikumpul ke email saya dengan kode 6YH-128-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 6","25 April 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program sederhana dengan menggunakan fragment, tugas dikumpul ke email saya dengan kode 6YH-129-NIM"));
//        listAssigment.add(new Assigment("Tugas Mockup Tugas Besar","1 Mei 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah mockup dari ide yang anda kemukakan dalam project tugas besar, tugas dikumpul ke email saya dengan kode 6YH-130-NIM"));
//        listAssigment.add(new Assigment("Tugas Laporan Progress Tugas Besar","6 Mei 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah Laporan progress dari tugas besar yang kelompok anda kerjakan, tugas dikumpul ke email saya dengan kode 6YH-131-NIM"));
//        listAssigment.add(new Assigment("Tugas Laporan Tugas Besar","18 Mei 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Kirimkan Laporan Project tugas besar yang kelompok anda kerjakan, tugas dikumpul ke email saya dengan kode 6YH-132-NIM"));
    }
}
