package com.example.ruangkelas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

        editTextNewMahasiswa=(EditText) v.findViewById(R.id.newMahasiswa);
        editTextNewNIM=(EditText) v.findViewById(R.id.newNIM);
        Button btAddMahasiswa=(Button) v.findViewById(R.id.saveMahasiswa);
        btAddMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMhsName=editTextNewMahasiswa.getText().toString();
                String newMhsNIM=editTextNewNIM.getText().toString();
                // add new item to arraylist
                listMahasiswa.add(new Mahasiswa("" + newMhsName,"" + newMhsNIM,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
                // notify listview of data changed
                mhsAdapter.notifyDataSetChanged();
            }
        });

        recyclerView2 = (RecyclerView) v.findViewById(R.id.rec_Dosen);
        dsnAdapter = new DosenAdapter(getContext(), listDosen);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setAdapter(dsnAdapter);

        editTextNewDosen=(EditText) v.findViewById(R.id.newDosen);
        Button btAddDosen=(Button) v.findViewById(R.id.saveDosen);
        btAddDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDsn=editTextNewDosen.getText().toString();
                // add new item to arraylist
                listDosen.add(new Dosen("" + newDsn,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
                // notify listview of data changed
                dsnAdapter.notifyDataSetChanged();
            }
        });

        TextView buttonBckMember = v.findViewById(R.id.bckMember);
        buttonBckMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMahasiswa = new ArrayList<>();
        listMahasiswa.add(new Mahasiswa("I Made Ari Wiradana","1705552030","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Gusti Agung Mayun Kukuh Jaluwana","1705552032","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Gusti Ayu Made Pratiwi Ashari","1705552034","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Bilal Suryananda","1705552035","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Dewa Komang Divha Pramartha","1705552037","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Made Toby Sathya Pratika","1705552038","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Zuraida Malini Cantika Riskiyanti","1705552041","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Nyoman Aditya Mahendra","1705552043","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Gusti Ngurah Bagus Prasetya Wijaya","1705552044","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Dwiki Krisnanda Wardy","1705552045","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Luh Ade Ratna Amertasuli","1705552046","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Made Revan Agastya","1705552047","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Gede Abdy Wisnu Wicaksana","1705552050","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Putu Ayu Citra Pratiwi","1705552051","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Made Juni Artana","1705552052","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Fariskha Annima Syaiful","1705552053","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Rizki Aditiya Gilang Ramadhan","1705552055","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Gusti Ngurah Agung Wahyu Aditya Pramana","1705552056","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Ni Putu Nirmala Dewi Widhiasih","1705552057","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Putu Bagus Swizaputra","1705552058","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("I Putu Adi Wira Kusuma","1705552059","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("James Vijayendra","1705552060","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listMahasiswa.add(new Mahasiswa("Ni Kadek Ratna Sari","1705552061","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));


        listDosen = new ArrayList<>();
        listDosen.add(new Dosen("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
    }
}