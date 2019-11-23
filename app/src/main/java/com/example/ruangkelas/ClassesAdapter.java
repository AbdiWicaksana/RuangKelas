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

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {

    Context context;
    List<Classes> listClasses;

    public ClassesAdapter(Context context, List<Classes> listClasses) {
        this.context = context;
        this.listClasses = listClasses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.kelasNama.setText(listClasses.get(position).getNamaKelas());
        holder.kelasSubjek.setText(listClasses.get(position).getSubjekKelas());
        holder.kelasAuthor.setText(listClasses.get(position).getAuthorKelas());
        holder.kelasNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        holder.kelasRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listClasses.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,listClasses.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClasses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kelasNama;
        TextView kelasSubjek;
        TextView kelasAuthor;
        TextView kelasRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            kelasNama = (TextView) itemView.findViewById(R.id.namaClasses);
            kelasSubjek = (TextView) itemView.findViewById(R.id.subjectClasses);
            kelasAuthor = (TextView) itemView.findViewById(R.id.authorClasses);
            kelasRemove = (TextView) itemView.findViewById(R.id.rmvClass);
        }
    }
}