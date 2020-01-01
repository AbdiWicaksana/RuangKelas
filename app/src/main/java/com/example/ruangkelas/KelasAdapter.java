package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ruangkelas.app.AppController;
import com.example.ruangkelas.data.Kelas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {

    private Context context;
    private List<Kelas> list;
    ProgressDialog pDialog;
    int success;

    private static final String TAG = KelasAdapter.class.getSimpleName();

    private static String url_delete       = Server.URL + "delete_kelas.php";

    public static final String TAG_ID_KELAS       = "id_kelas";
    public static final String TAG_NAMA             = "nama";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public KelasAdapter(Context context, List<Kelas> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kelas kelas = list.get(position);

        final String id_kelas = kelas.getId().toString();

        holder.textNamaKelas.setText(kelas.getNama_kelas());
        holder.textSubjectKelas.setText(String.valueOf(kelas.getSubject_kelas()));
        holder.textAuthorKelas.setText(String.valueOf(kelas.getAuthor_kelas()));

        holder.textNamaKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_kelas", id_kelas);
//                Toast.makeText(context, id_kelas, Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });

        holder.kelasdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKelas(id_kelas);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNamaKelas, textSubjectKelas, textAuthorKelas, kelasdelete;

        public ViewHolder(View itemView) {
            super(itemView);

            textNamaKelas = itemView.findViewById(R.id.namaClasses);
            textSubjectKelas = itemView.findViewById(R.id.subjectClasses);
            textAuthorKelas = itemView.findViewById(R.id.authorClasses);
            kelasdelete = itemView.findViewById(R.id.rmvClass);
        }
    }

    private void deleteKelas(final String id_kelas){
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
//        showDialog();

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
                params.put("id_kelas", id_kelas);

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