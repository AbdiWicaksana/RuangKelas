package com.example.ruangkelas;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ruangkelas.data.Member;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemberUserFragment extends Fragment {
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

    public MemberUserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_member_user,container,false);

//        recyclerView = (RecyclerView) v.findViewById(R.id.rec_MahasiswaUser);
//        mhsAdapter = new MahasiswaAdapter(getContext(), listMahasiswa);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(mhsAdapter);

        mList = v.findViewById(R.id.rec_MahasiswaUser);

        memberList = new ArrayList<>();
        adapter = new MemberUserAdapter(getActivity().getApplicationContext(),memberList);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        final String id_kelas = getActivity().getIntent().getStringExtra("id_kelas");
        getData(id_kelas);

        recyclerView2 = (RecyclerView) v.findViewById(R.id.rec_DosenUser);
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
//        listMahasiswa.add(new Mahasiswa("I Made Ari Wiradana","1705552030","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Gusti Agung Mayun Kukuh Jaluwana","1705552032","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Gusti Ayu Made Pratiwi Ashari","1705552034","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Bilal Suryananda","1705552035","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Dewa Komang Divha Pramartha","1705552037","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Made Toby Sathya Pratika","1705552038","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Zuraida Malini Cantika Riskiyanti","1705552041","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Nyoman Aditya Mahendra","1705552043","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Gusti Ngurah Bagus Prasetya Wijaya","1705552044","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Dwiki Krisnanda Wardy","1705552045","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Luh Ade Ratna Amertasuli","1705552046","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Made Revan Agastya","1705552047","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Gede Abdy Wisnu Wicaksana","1705552050","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Putu Ayu Citra Pratiwi","1705552051","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Made Juni Artana","1705552052","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Fariskha Annima Syaiful","1705552053","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Rizki Aditiya Gilang Ramadhan","1705552055","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Gusti Ngurah Agung Wahyu Aditya Pramana","1705552056","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Ni Putu Nirmala Dewi Widhiasih","1705552057","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Putu Bagus Swizaputra","1705552058","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("I Putu Adi Wira Kusuma","1705552059","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("James Vijayendra","1705552060","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listMahasiswa.add(new Mahasiswa("Ni Kadek Ratna Sari","1705552061","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//
//
        listDosen = new ArrayList<>();
        listDosen.add(new Dosen("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
    }
}
