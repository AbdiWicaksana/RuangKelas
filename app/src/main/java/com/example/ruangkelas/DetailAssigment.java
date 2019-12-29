package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.ruangkelas.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailAssigment extends AppCompatActivity {

    ProgressDialog pDialog;
    int success;
    TextView namaTgs, judulDetailTgs;

    private static final String TAG = CommentTimelineActivity.class.getSimpleName();

    private static String url_get_assignment       = Server.URL + "get_detail_assignment.php";

    public static final String TAG_ID_ASSIGNMENT    = "id_assignment";
    public static final String TAG_NAMA             = "nama";
    public static final String TAG_ASSIGNMENT       = "assignment";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_assigment);

        namaTgs = findViewById(R.id.namaTgs);
        judulDetailTgs = findViewById(R.id.judulDetailTgs);

        final String id_assignment = DetailAssigment.this.getIntent().getStringExtra("id_assignment");

        getAssignment(id_assignment);

//        getIncomingIntent();

        TextView buttonBckDetailAssign = findViewById(R.id.bckDtlAssign);
        buttonBckDetailAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAssignment(final String id_assignment){
        pDialog = new ProgressDialog(DetailAssigment.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_get_assignment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response" + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.d("get announce data", jObj.toString());
//                        String id = (jObj.getString(TAG_ID_ASSIGNMENT));
                        String nama = (jObj.getString(TAG_NAMA));
                        String assignment = (jObj.getString(TAG_ASSIGNMENT));

                        if (!id_assignment.isEmpty()) {
                            namaTgs.setText(nama);
                            judulDetailTgs.setText(assignment);

                        } else {
                            Toast.makeText(DetailAssigment.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(DetailAssigment.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(DetailAssigment.this, error.getMessage(), Toast.LENGTH_LONG).show();

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

//    private void getIncomingIntent(){
//        if (getIntent().hasExtra("link") && getIntent().hasExtra("nama") && getIntent().hasExtra("date") &&  getIntent().hasExtra("detail")){
//
//            String Link = getIntent().getStringExtra("link");
//            String Nama = getIntent().getStringExtra("nama");
//            String Date = getIntent().getStringExtra("date");
//            String Detail = getIntent().getStringExtra("detail");
//            setData(Link,Nama,Date,Detail);
//        }
//    }
//
//    private void setData(String Link, String Nama, String Date, String Detail){
//        TextView namaDetail = findViewById(R.id.namaTgs);
//        namaDetail.setText(Nama);
//        TextView posisiDetail = findViewById(R.id.dateTgs);
//        posisiDetail.setText(Date);
//        TextView det = findViewById(R.id.judulDetailTgs);
//        det.setText(Detail);
//
//        ImageView gambarDetail = findViewById(R.id.imgTugas);
//        Glide.with(this)
//                .asBitmap()
//                .load(Link)
//                .into(gambarDetail);
//    }
}
