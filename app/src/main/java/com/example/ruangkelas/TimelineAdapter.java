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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.ruangkelas.app.AppController;
import com.example.ruangkelas.data.Member;
import com.example.ruangkelas.data.Timeline;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> {

    Context context;
    List<Timeline> listTimeline;
    ProgressDialog pDialog;
    int success;

    private static final String TAG = TimelineAdapter.class.getSimpleName();

    private static String url_delete       = Server.URL + "delete_announce.php";

    public static final String TAG_ID_ASSIGNMENT    = "id_announce";
    public static final String TAG_NAMA             = "nama";
    public static final String TAG_ASSIGNMENT       = "assignment";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

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

        Timeline timeline = listTimeline.get(position);

        final String id_announce = timeline.getId().toString();

        holder.nameSender.setText(String.valueOf(timeline.getNama_user()));
        holder.nameAnouncement.setText(String.valueOf(timeline.getTitle()));
        holder.deskAnouncement.setText(String.valueOf(timeline.getAnnounce()));
        Picasso.get().load(timeline.getPhoto()).centerCrop().fit().into(holder.photo);

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

        holder.tlRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAnnounce(id_announce);
//                Toast.makeText(context, id_announce, Toast.LENGTH_LONG).show();
            }
        });


//        Glide.with(context)
//                .asBitmap()
//                .load(listTimeline.get(position).getFotoSender())
//                .into(holder.imageSender);
//        holder.nameSender.setText(listTimeline.get(position).getNamaSender());
//        holder.nameAnouncement.setText(listTimeline.get(position).getNamaAnouncement());
//        holder.deskAnouncement.setText(listTimeline.get(position).getDescAnouncement());
//
//        holder.nameAnouncement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, CommentTimelineActivity.class);
//                context.startActivity(intent);
//            }
//        });
//
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

        TextView nameSender, nameAnouncement, deskAnouncement;
        ImageView imageSender, photo;
        TextView tlRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            nameSender = (TextView) itemView.findViewById(R.id.namaPengirim);
            nameAnouncement = (TextView) itemView.findViewById(R.id.namaPengumuman);
            deskAnouncement = (TextView) itemView.findViewById(R.id.deskPengumuman);
//            imageSender = (ImageView) itemView.findViewById(R.id.imagePengirim);
            tlRemove = (TextView) itemView.findViewById(R.id.rmvTimeline);
            photo = itemView.findViewById(R.id.photo_profile);
        }
    }

    private void deleteAnnounce(final String id_announce){
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response" + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.d("get announce data", jObj.toString());
                        Toast.makeText(context, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                        String id = (jObj.getString(TAG_ID_ASSIGNMENT));
//                        String nama = (jObj.getString(TAG_NAMA));
//                        String assignment = (jObj.getString(TAG_ASSIGNMENT));

//                        if (!id_assignment.isEmpty()) {
//                            namaTgs.setText(nama);
//                            judulDetailTgs.setText(assignment);

//                        } else {
//                            Toast.makeText(TimelineAdapter.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                        }

                    } else {
                        Toast.makeText(context, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_announce", id_announce);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}