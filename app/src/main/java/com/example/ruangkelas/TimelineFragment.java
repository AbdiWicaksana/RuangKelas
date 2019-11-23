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

public class TimelineFragment extends Fragment {
    View v3;
    RecyclerView recyclerView;
    List<Timeline> listTimeline;
    private TimelineAdapter tlAdapter;
    EditText editTextNewSndr;
    EditText editTextNewTtlAnn;
    EditText editTextNewAnn;

    public TimelineFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v3 = inflater.inflate(R.layout.timeline_layout,container,false);
        recyclerView = (RecyclerView) v3.findViewById(R.id.rec_pengumuman);
        recyclerView.setHasFixedSize(true);
        tlAdapter = new TimelineAdapter(getContext(), listTimeline);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tlAdapter);

        editTextNewSndr=(EditText) v3.findViewById(R.id.newPengirim);
        editTextNewTtlAnn=(EditText) v3.findViewById(R.id.newTitleAnnounce);
        editTextNewAnn=(EditText) v3.findViewById(R.id.newAnnounce);
        Button btAdd=(Button) v3.findViewById(R.id.saveTimeline);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                String newSndr=editTextNewSndr.getText().toString();
                String newTtlAnn=editTextNewTtlAnn.getText().toString();
                String newAnn=editTextNewAnn.getText().toString();
                // add new item to arraylist
                listTimeline.add(new Timeline("" + newSndr,"" + newTtlAnn, "" + newAnn,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listTimeline = new ArrayList<>();
        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Jadwal Pengganti","Kuliah hari senin besok diganti ke hari rabu jam 10.00 WITA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Tugas Tambahan","Kuliah hari senin besok Saya tidak bisa hadir harap untuk mengecek tugas di Assignment","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Kuliah Tambahan","Kuliah tambahan diadakan pada jumat jam 10.00 WITA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Kuliah di Liburkan","Kuliah hari senin besok ditiadakan dan untuk korti mohon untuk mencari jadwal pengganti","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
    }

}
