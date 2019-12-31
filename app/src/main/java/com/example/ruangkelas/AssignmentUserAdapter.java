package com.example.ruangkelas;

import android.app.ProgressDialog;
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
import com.example.ruangkelas.data.Assignment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AssignmentUserAdapter extends RecyclerView.Adapter<AssignmentUserAdapter.MyViewHolder> {

    Context context;
    List<Assignment> listAssigment;
    ProgressDialog pDialog;
    int success;

    private static final String TAG = CommentTimelineActivity.class.getSimpleName();

    private static String url_delete       = Server.URL + "delete_assignment.php";

    public static final String TAG_ID_ASSIGNMENT    = "id_announce";
    public static final String TAG_NAMA             = "nama";
    public static final String TAG_ASSIGNMENT       = "assignment";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";


    public AssignmentUserAdapter(Context context, List<Assignment> listAssigment) {
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
        Assignment assignment = listAssigment.get(position);

        final String id_assignment = assignment.getId().toString();
//        Glide.with(context)
//                .asBitmap()
//                .load(listAssigment.get(position).getFotoAssigment())
//                .into(holder.fotoTugas);
        holder.namaTugas.setText(listAssigment.get(position).getNama_assignment());
        holder.tanggalTugas.setText(listAssigment.get(position).getDate_assignment());
        Picasso.get().load(assignment.getPhoto()).centerCrop().fit().into(holder.photo);

        holder.namaTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAssigment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_assignment", id_assignment);
                Toast.makeText(context, id_assignment, Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAssigment.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namaTugas,tanggalTugas;
        ImageView fotoTugas, photo;


        public MyViewHolder(View itemView) {
            super(itemView);

            namaTugas = (TextView) itemView.findViewById(R.id.nameTugasUser);
            tanggalTugas = (TextView) itemView.findViewById(R.id.dateTugasUser);
//            fotoTugas = (ImageView) itemView.findViewById(R.id.imageTugasUser);
            photo = itemView.findViewById(R.id.photo_profile);
        }
    }
}