package com.example.ruangkelas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruangkelas.data.Dosen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.MyViewHolder> {

    Context context;
    List<Dosen> listDosen;

    public DosenAdapter(Context context, List<Dosen> listDosen) {
        this.context = context;
        this.listDosen = listDosen;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v2;
        v2 = LayoutInflater.from(context).inflate(R.layout.item_dosen, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v2);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Dosen dosen = listDosen.get(position);
//        Glide.with(context)
//                .asBitmap()
//                .load(listDosen.get(position).getFotoDosen())
//                .into(holder.photo);
        holder.nama.setText(listDosen.get(position).getNama());
        Picasso.get().load(dosen.getPhoto()).centerCrop().fit().into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return listDosen.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama;
        ImageView photo;


        public MyViewHolder(View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.namaDosen);
            photo = (ImageView) itemView.findViewById(R.id.photo_profile);
        }
    }
}