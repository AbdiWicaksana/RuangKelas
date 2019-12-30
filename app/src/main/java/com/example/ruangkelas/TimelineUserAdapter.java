package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ruangkelas.data.Timeline;

import java.util.List;

public class TimelineUserAdapter extends RecyclerView.Adapter<TimelineUserAdapter.MyViewHolder> {

    Context context;
    List<Timeline> listTimeline;

    ProgressDialog pDialog;
    int success;

    private static final String TAG = CommentTimelineActivity.class.getSimpleName();

    private static String url_delete       = Server.URL + "delete_announce.php";

    public static final String TAG_ID_ASSIGNMENT    = "id_announce";
    public static final String TAG_NAMA             = "nama";
    public static final String TAG_ASSIGNMENT       = "assignment";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public TimelineUserAdapter(Context context, List<Timeline> listTimeline) {
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
        Timeline timeline = listTimeline.get(position);

        final String id_announce = timeline.getId().toString();

//        Glide.with(context)
//                .asBitmap()
//                .load(listTimeline.get(position).getFotoSender())
//                .into(holder.imageSender);
        holder.nameSender.setText(String.valueOf(timeline.getNama_user()));
        holder.nameAnouncement.setText(String.valueOf(timeline.getTitle()));
        holder.deskAnouncement.setText(String.valueOf(timeline.getAnnounce()));

        holder.nameSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentTimelineActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_announce", id_announce);
//                Toast.makeText(context, id_announce, Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
//        holder.tlRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listTimeline.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position,listTimeline.size());
//            }
//        });
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