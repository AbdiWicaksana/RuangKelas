package com.example.ruangkelas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    Context context;
    List<Mahasiswa> listMahasiswa;

    public MahasiswaAdapter(Context context, List<Mahasiswa> listMahasiswa) {
        this.context = context;
        this.listMahasiswa = listMahasiswa;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(listMahasiswa.get(position).getFotoMahasiswa())
                .into(holder.imageView);
        holder.name.setText(listMahasiswa.get(position).getNamaMahasiswa());
        holder.nim.setText(listMahasiswa.get(position).getNim());

        holder.mhsRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listMahasiswa.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,listMahasiswa.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView nim;
        ImageView imageView;
        TextView mhsRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.namaMember);
            nim = (TextView) itemView.findViewById(R.id.nimMember);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            mhsRemove = (TextView) itemView.findViewById(R.id.rmvMahasiswa);
        }
    }
}