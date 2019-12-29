package com.example.ruangkelas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
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

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    Context context;
    List<Comment> listComment;

    public CommentAdapter(Context context, List<Comment> listComment) {
        this.context = context;
        this.listComment = listComment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v2;
        v2 = LayoutInflater.from(context).inflate(R.layout.comment, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v2);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Comment comment = listComment.get(position);

        holder.namaPembuatCom.setText(comment.getNamaPengCom());
        holder.isiCom.setText(comment.getComPeng());
//        Glide.with(context)
//                .asBitmap()
//                .load(listComment.get(position).getFotoPengCom())
//                .into(holder.fotoPembuatCom);
//        holder.namaPembuatCom.setText(listComment.get(position).getNamaPengCom());
//        holder.isiCom.setText(listComment.get(position).getComPeng());
//
//        holder.comRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listComment.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position,listComment.size());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView fotoPembuatCom;
        TextView namaPembuatCom;
        TextView isiCom;
        TextView comRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            fotoPembuatCom = (ImageView) itemView.findViewById(R.id.imagePengirimCom);
            namaPembuatCom = (TextView) itemView.findViewById(R.id.namaPengirimCom);
            isiCom = (TextView) itemView.findViewById(R.id.isiCom);
            comRemove = (TextView) itemView.findViewById(R.id.rmvCom);
        }
    }
}