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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(listDosen.get(position).getFotoDosen())
                .into(holder.dosenFoto);
        holder.dosenNama.setText(listDosen.get(position).getNamaDosen());

        holder.dsnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listDosen.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,listDosen.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDosen.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dosenNama;
        ImageView dosenFoto;
        TextView dsnRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            dosenNama = (TextView) itemView.findViewById(R.id.namaDosen);
            dosenFoto = (ImageView) itemView.findViewById(R.id.image_dosen);
            dsnRemove = (TextView) itemView.findViewById(R.id.rmvDosen);
        }
    }
}