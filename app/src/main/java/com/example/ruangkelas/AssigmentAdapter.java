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
import com.example.ruangkelas.data.Assignment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssigmentAdapter extends RecyclerView.Adapter<AssigmentAdapter.MyViewHolder> {

    Context context;
    List<Assignment> listAssignment;
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

    public AssigmentAdapter(Context context, List<Assignment> listAssignment) {
        this.context = context;
        this.listAssignment = listAssignment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v2;
        v2 = LayoutInflater.from(context).inflate(R.layout.assignment, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v2);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Assignment assignment = listAssignment.get(position);

        final String id_assignment = assignment.getId().toString();
//        Glide.with(context)
//                .asBitmap()
//                .load(listAssigment.get(position).getFotoAssigment())
//                .into(holder.fotoTugas);
        holder.namaTugas.setText(listAssignment.get(position).getNama_assignment());
        holder.tanggalTugas.setText(listAssignment.get(position).getDate_assignment());

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

        holder.assignRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAssignment(id_assignment);
            }
        });

//        holder.namaTugas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailAssigment.class);
//                intent.putExtra("link",listAssigment.get(position).getFotoAssigment());
//                intent.putExtra("nama",listAssigment.get(position).getNamaAssigment());
//                intent.putExtra("date",listAssigment.get(position).getTanggalAssigment());
//                intent.putExtra("detail",listAssigment.get(position).getDetailAssigment());
//                context.startActivity(intent);
//            }
//        });
//
//        holder.assignRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listAssigment.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position,listAssigment.size());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listAssignment.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namaTugas;
        TextView tanggalTugas;
        ImageView fotoTugas;
        TextView assignRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            namaTugas = (TextView) itemView.findViewById(R.id.nameTugas);
            tanggalTugas = (TextView) itemView.findViewById(R.id.dateTugas);
            fotoTugas = (ImageView) itemView.findViewById(R.id.imageTugas);
            assignRemove = (TextView) itemView.findViewById(R.id.rmvAssign);
        }
    }

    private void deleteAssignment(final String id_assignment){
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
                params.put("id_assignment", id_assignment);

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