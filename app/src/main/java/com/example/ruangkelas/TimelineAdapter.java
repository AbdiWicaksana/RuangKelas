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

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> {

    Context context;
    List<Timeline> listTimeline;

    public TimelineAdapter(Context context, List<Timeline> listTimeline) {
        this.context = context;
        this.listTimeline = listTimeline;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v3;
        v3 = LayoutInflater.from(context).inflate(R.layout.timeline, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v3);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(listTimeline.get(position).getFotoSender())
                .into(holder.imageSender);
        holder.nameSender.setText(listTimeline.get(position).getNamaSender());
        holder.nameAnouncement.setText(listTimeline.get(position).getNamaAnouncement());
        holder.deskAnouncement.setText(listTimeline.get(position).getDescAnouncement());

        holder.tlRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listTimeline.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,listTimeline.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTimeline.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameSender;
        TextView nameAnouncement;
        TextView deskAnouncement;
        ImageView imageSender;
        TextView tlRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            nameSender = (TextView) itemView.findViewById(R.id.namaPengirim);
            nameAnouncement = (TextView) itemView.findViewById(R.id.namaPengumuman);
            deskAnouncement = (TextView) itemView.findViewById(R.id.deskPengumuman);
            imageSender = (ImageView) itemView.findViewById(R.id.imagePengirim);
            tlRemove = (TextView) itemView.findViewById(R.id.rmvTimeline);
        }
    }
}