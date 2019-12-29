package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.ruangkelas.data.Announce;
import com.example.ruangkelas.data.Timeline;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimelineFragment extends Fragment {
    View v3;
    RecyclerView recyclerView;
    List<Timeline> listTimeline;
    TextView announce;
    private TimelineAdapter tlAdapter;
    EditText editTextNewSndr;
    EditText editTextNewTtlAnn;
    EditText editTextNewAnn;
    ProgressDialog pDialog;
    String id_user;
    SharedPreferences sharedpreferences;
    int success;

    private RecyclerView tList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Timeline> timelineList;
    private RecyclerView.Adapter adapter;


    private static final String url_add = Server.URL + "add_announce.php";
    private static final String url_get = Server.URL + "get_announce.php";
    private static final String TAG = HomeActivityAdmin.class.getSimpleName();

    public static final String TAG_ID = "id";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public TimelineFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v3 = inflater.inflate(R.layout.timeline_layout,container,false);
//        recyclerView = (RecyclerView) v3.findViewById(R.id.rec_pengumuman);
//        recyclerView.setHasFixedSize(true);
//        tlAdapter = new TimelineAdapter(getContext(), listTimeline);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(tlAdapter);
        announce = (TextView) v3.findViewById(R.id.announce);

        tList = v3.findViewById(R.id.rec_pengumuman);

        timelineList = new ArrayList<>();
        adapter = new TimelineAdapter(getActivity(),timelineList);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(tList.getContext(), linearLayoutManager.getOrientation());

        tList.setHasFixedSize(true);
        tList.setLayoutManager(linearLayoutManager);
        tList.addItemDecoration(dividerItemDecoration);
        tList.setAdapter(adapter);


//        editTextNewSndr=(EditText) v3.findViewById(R.id.newPengirim);
        editTextNewTtlAnn=(EditText) v3.findViewById(R.id.newTitleAnnounce);
        editTextNewAnn=(EditText) v3.findViewById(R.id.newAnnounce);
        Button btAdd=(Button) v3.findViewById(R.id.saveTimeline);

        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id_user = sharedpreferences.getString(TAG_ID, null);

        final String id_kelas = getActivity().getIntent().getStringExtra("id_kelas");

        getData(id_kelas);

        Toast.makeText(getActivity(), id_kelas, Toast.LENGTH_LONG).show();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
//                String newSndr=editTextNewSndr.getText().toString();

                String newTtlAnn=editTextNewTtlAnn.getText().toString();
                String newAnn=editTextNewAnn.getText().toString();

                Toast.makeText(getActivity(), id_user, Toast.LENGTH_LONG).show();

                addAnnounce(id_user, id_kelas, newTtlAnn, newAnn);
                // add new item to arraylist
//                listTimeline.add(new Timeline("" + newSndr,"" + newTtlAnn, "" + newAnn,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
                // notify listview of data changed
                tlAdapter.notifyDataSetChanged();
            }

        });

        TextView buttonBckTimeline = v3.findViewById(R.id.bckTimeline);
        buttonBckTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v3;
    }

    private void addAnnounce(final String id_user, final String id_kelas, final String newTtlAnn, final String newAnn) {
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
                params.put("id_user", id_user);
                params.put("id_kelas", id_kelas);
                params.put("title", newTtlAnn);
                params.put("announce", newAnn);

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

                        Timeline timeline = new Timeline();
                        timeline.setId(jsonObject.getInt("id"));
                        timeline.setNama_user(jsonObject.getString("nama"));
                        timeline.setTitle(jsonObject.getString("title"));
                        timeline.setAnnounce(jsonObject.getString("announce"));

                        timelineList.add(timeline);
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

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        listTimeline = new ArrayList<>();
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Jadwal Pengganti","Kuliah hari senin besok diganti ke hari rabu jam 10.00 WITA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Tugas Tambahan","Kuliah hari senin besok Saya tidak bisa hadir harap untuk mengecek tugas di Assignment","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Kuliah Tambahan","Kuliah tambahan diadakan pada jumat jam 10.00 WITA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Kuliah di Liburkan","Kuliah hari senin besok ditiadakan dan untuk korti mohon untuk mencari jadwal pengganti","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//    }

}
