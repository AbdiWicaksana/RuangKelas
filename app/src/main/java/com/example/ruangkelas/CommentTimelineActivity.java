package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ruangkelas.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentTimelineActivity extends AppCompatActivity{
    List<Comment> listComment;
    public CommentAdapter comAdapter;

    int success;
    ProgressDialog pDialog;
    SharedPreferences sharedpreferences;
    String id;

    private static final String TAG = CommentTimelineActivity.class.getSimpleName();

    private static String url_get_announce       = Server.URL + "get_detail_announce.php";

    public static final String TAG_ID           = "id";

    public static final String TAG_NAMA         = "nama";
    public static final String TAG_NIM          = "nim";
    public static final String TAG_EMAIL        = "email";
    public static final String TAG_USERNAME     = "username";
    public static final String TAG_PASSWORD     = "password";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_timeline);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);

        listComment = new ArrayList<>();
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        showComment();
    }

//    private void getAnnounce(final String id){
//        pDialog = new ProgressDialog(CommentTimelineActivity.this);
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Loading...");
//        showDialog();
//
//        StringRequest strReq = new StringRequest(Request.Method.POST, url_get_announce, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "Response" + response.toString());
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    success = jObj.getInt(TAG_SUCCESS);
//
//                    if (success == 1) {
//                        Log.d("get edit data", jObj.toString());
//                        String id = (jObj.getString(TAG_ID));
//                        String nama = (jObj.getString(TAG_NAMA));
//                        String nim = (jObj.getString(TAG_NIM));
//                        String email = (jObj.getString(TAG_EMAIL));
//                        String username = (jObj.getString(TAG_USERNAME));
//                        String password = (jObj.getString(TAG_PASSWORD));
//
//                        if (!id.isEmpty()) {
//                            txt_nama.setText(nama);
//                            txt_nim.setText(nim);
//                            txt_email.setText(email);
//                            txt_username.setText(username);
//                            txt_password.setText(password);
//
//                        } else {
//                            Toast.makeText(EditProfile.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                        }
//
//                    } else {
//                        Toast.makeText(EditProfile.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                hideDialog();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error: " + error.getMessage());
//                Toast.makeText(EditProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();
//
//                hideDialog();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters ke post url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("id", id);
//
//                return params;
//            }
//
//        };
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
//    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showComment() {
        RecyclerView recyclerView = findViewById(R.id.rec_comment);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        comAdapter = new CommentAdapter(this, listComment);
        recyclerView.setAdapter(comAdapter);
    }
}
