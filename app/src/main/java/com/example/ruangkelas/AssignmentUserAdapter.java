package com.example.ruangkelas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class AssignmentUserAdapter extends RecyclerView.Adapter<AssignmentUserAdapter.MyViewHolder> {

    Context context;
    List<Assigment> listAssigment;

    public AssignmentUserAdapter(Context context, List<Assigment> listAssigment) {
        this.context = context;
        this.listAssigment = listAssigment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v2;
        v2 = LayoutInflater.from(context).inflate(R.layout.assignment_user, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v2);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(listAssigment.get(position).getFotoAssigment())
                .into(holder.fotoTugas);
        holder.namaTugas.setText(listAssigment.get(position).getNamaAssigment());
        holder.tanggalTugas.setText(listAssigment.get(position).getTanggalAssigment());

        holder.namaTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailAssigment.class);
                intent.putExtra("link",listAssigment.get(position).getFotoAssigment());
                intent.putExtra("nama",listAssigment.get(position).getNamaAssigment());
                intent.putExtra("date",listAssigment.get(position).getTanggalAssigment());
                intent.putExtra("detail",listAssigment.get(position).getDetailAssigment());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAssigment.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namaTugas;
        TextView tanggalTugas;
        ImageView fotoTugas;


        public MyViewHolder(View itemView) {
            super(itemView);

            namaTugas = (TextView) itemView.findViewById(R.id.nameTugasUser);
            tanggalTugas = (TextView) itemView.findViewById(R.id.dateTugasUser);
            fotoTugas = (ImageView) itemView.findViewById(R.id.imageTugasUser);
        }
    }
}