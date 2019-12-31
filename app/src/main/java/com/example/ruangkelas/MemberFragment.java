package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
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
import com.example.ruangkelas.data.Member;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    List<Mahasiswa> listMahasiswa;
    List<Dosen> listDosen;
    private DosenAdapter dsnAdapter;
    private MahasiswaAdapter mhsAdapter;
    EditText editTextNewDosen;
    EditText editTextNewMahasiswa;
    EditText editTextNewNIM;
    ProgressDialog pDialog;
    int success;
    Intent intent;
    String id;
    SharedPreferences sharedPreferences;
    ConnectivityManager conMgr;

    public static final String my_shared_preferences = "my_shared_preferences";

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Member> memberList;
    private RecyclerView.Adapter adapter;

    private static final String url_add = Server.URL + "add_member.php";
    private static final String url_get = Server.URL + "get_member.php";
    private static final String TAG = HomeActivityAdmin.class.getSimpleName();

    public static final String TAG_ID = "id";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public MemberFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.member_layout,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rec_Mahasiswa);
        mhsAdapter = new MahasiswaAdapter(getContext(), listMahasiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mhsAdapter);

        mList = v.findViewById(R.id.rec_Mahasiswa);

        memberList = new ArrayList<>();
        adapter = new MemberAdapter(getActivity().getApplicationContext(),memberList);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

//        editTextNewMahasiswa=(EditText) v.findViewById(R.id.newMahasiswa);
        editTextNewNIM=(EditText) v.findViewById(R.id.newNIM);
        Button btAddMahasiswa=(Button) v.findViewById(R.id.saveMahasiswa);

        final String id_kelas = getActivity().getIntent().getStringExtra("id_kelas");
        getData(id_kelas);

        btAddMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = editTextNewNIM.getText().toString();

                addMember(id_kelas, nim);
////                String newMhsName=editTextNewMahasiswa.getText().toString();
//                String newMhsNIM=editTextNewNIM.getText().toString();
//                // add new item to arraylist
////                listMahasiswa.add(new Mahasiswa("" + newMhsName,"" + newMhsNIM,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//                // notify listview of data changed
//                mhsAdapter.notifyDataSetChanged();
            }
        });

        recyclerView2 = (RecyclerView) v.findViewById(R.id.rec_Dosen);
        dsnAdapter = new DosenAdapter(getContext(), listDosen);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setAdapter(dsnAdapter);



        TextView buttonBckMember = v.findViewById(R.id.bckMember);
        buttonBckMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v;
    }

    private void addMember(final String id_kelas, final String nim) {
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

                        editTextNewNIM.setText("");

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
                params.put("nim", nim);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void getData(final String id_kelas) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url_get + "?id_kelas=" + id_kelas, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Member member = new Member();
                        member.setNama(jsonObject.getString("nama"));
                        member.setNim(jsonObject.getString("nim"));
                        member.setPhoto(jsonObject.getString("photo"));

                        memberList.add(member);
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
//        listMahasiswa = new ArrayList<>();

        listDosen = new ArrayList<>();
        listDosen.add(new Dosen("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
    }
}