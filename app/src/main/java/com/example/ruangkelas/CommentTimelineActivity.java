package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ruangkelas.app.AppController;

import org.json.JSONArray;
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
    TextView txt_nama, txt_title, txt_announce;
    EditText txt_comment;
    ImageView btn_send;
    ProgressDialog pDialog;
    SharedPreferences sharedpreferences;
    String id;

    private RecyclerView cList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Comment> commentList;
    private RecyclerView.Adapter adapter;


    private static final String TAG = CommentTimelineActivity.class.getSimpleName();

    private static String url_get_announce       = Server.URL + "get_detail_announce.php";
    private static String url_comment       = Server.URL + "add_comment.php";
    private static String url_get_comment       = Server.URL + "get_comment.php";

    public static final String TAG_ID           = "id";
    public static final String TAG_ID_ANNOUNCE         = "id_announce";
    public static final String TAG_NAMA         = "nama";
    public static final String TAG_TITLE          = "title";
    public static final String TAG_ANNOUNCE       = "announce";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_timeline);

        cList = findViewById(R.id.rec_comment);

        commentList = new ArrayList<>();
        adapter = new CommentAdapter(getApplicationContext(),commentList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(cList.getContext(), linearLayoutManager.getOrientation());

        cList.setHasFixedSize(true);
        cList.setLayoutManager(linearLayoutManager);
        cList.addItemDecoration(dividerItemDecoration);
        cList.setAdapter(adapter);


        txt_nama = findViewById(R.id.txt_nama);
        txt_title = findViewById(R.id.txt_title);
        txt_announce = findViewById(R.id.txt_announce);
        txt_comment = findViewById(R.id.txt_comment);
        btn_send = findViewById(R.id.btn_send);

        TextView buttonBckComment = findViewById(R.id.bckComment);
        buttonBckComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final String id_announce = CommentTimelineActivity.this.getIntent().getStringExtra("id_announce");

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
                id = sharedpreferences.getString(TAG_ID, null);

                String comment = txt_comment.getText().toString();

                addComment(id, comment, id_announce);
            }
        });

        Toast.makeText(CommentTimelineActivity.this, id_announce, Toast.LENGTH_LONG).show();

        getAnnounce(id_announce);
        getData(id_announce);

//        listComment = new ArrayList<>();
//        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
//        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
//        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
//        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
//        showComment();
    }

    private void getData(final String id_announce) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url_get_comment + "?id_announce=" + id_announce, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Comment comment = new Comment();
                        comment.setNamaPengCom(jsonObject.getString("nama"));
                        comment.setComPeng(jsonObject.getString("comment"));

                        commentList.add(comment);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    private void addComment(final String id, final String comment, final String id_announce) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_comment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Daftar Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Comment!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_comment.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("comment", comment);
                params.put("id_announce", id_announce);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void getAnnounce(final String id_announce){
        pDialog = new ProgressDialog(CommentTimelineActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_get_announce, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response" + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.d("get announce data", jObj.toString());
                        String id_announce = (jObj.getString(TAG_ID_ANNOUNCE));
                        String nama = (jObj.getString(TAG_NAMA));
                        String title = (jObj.getString(TAG_TITLE));
                        String announce = (jObj.getString(TAG_ANNOUNCE));

                        if (!id_announce.isEmpty()) {
                            txt_nama.setText(nama);
                            txt_title.setText(title);
                            txt_announce.setText(announce);

                        } else {
                            Toast.makeText(CommentTimelineActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(CommentTimelineActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(CommentTimelineActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

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

//    private void showComment() {
//        RecyclerView recyclerView = findViewById(R.id.rec_comment);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        comAdapter = new CommentAdapter(this, listComment);
//        recyclerView.setAdapter(comAdapter);
//    }
}
