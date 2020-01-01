package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ruangkelas.app.AppController;
import com.example.ruangkelas.data.Timeline;
import com.example.ruangkelas.database.DbContract;
import com.example.ruangkelas.database.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimelineUserFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View v3;
    RecyclerView recyclerView;
    List<Timeline> listTimeline;
    private TimelineUserAdapter tlAdapter;
    EditText editTextNewSndr;
    EditText editTextNewTtlAnn;
    EditText editTextNewAnn;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    ProgressDialog pDialog;
    String id_user;
    SharedPreferences sharedpreferences;
    int success;

    ConnectivityManager conMgr;

    private RecyclerView tList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Timeline> timelineList;
    private RecyclerView.Adapter adapter;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAsZXkyqw:APA91bEo1lHbreDQqETM2MsovBtTD0-bRQFeiipIzwri1z-L2IAQxA_wW7AgyxPLYgJd2669boSb_jpKKcqp41ifPzzVMavBQcysRxdGRruGZyQ_i1T35CcZ1pFdp-XBbxFEZXR9cpP0";
    final private String contentType = "application/json";
    final String TAG_NOTIF = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    private static final String url_add = Server.URL + "add_announce.php";
    private static final String url_get = Server.URL + "get_announce.php";
    private static final String TAG = HomeActivityAdmin.class.getSimpleName();

    public static final String TAG_ID = "id";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public TimelineUserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v3 = inflater.inflate(R.layout.timeline_layout,container,false);
        recyclerView = (RecyclerView) v3.findViewById(R.id.rec_pengumuman);
        recyclerView.setHasFixedSize(true);
        tlAdapter = new TimelineUserAdapter(getContext(), listTimeline);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tlAdapter);
        mSwipeRefreshLayout = v3.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        tList = v3.findViewById(R.id.rec_pengumuman);

        timelineList = new ArrayList<>();
        adapter = new TimelineUserAdapter(getActivity(),timelineList);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(tList.getContext(), linearLayoutManager.getOrientation());

        tList.setHasFixedSize(true);
        tList.setLayoutManager(linearLayoutManager);
        tList.addItemDecoration(dividerItemDecoration);
        tList.setAdapter(adapter);

        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id_user = sharedpreferences.getString(TAG_ID, null);

        final String id_kelas = getActivity().getIntent().getStringExtra("id_kelas");

        conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                getData(id_kelas);
            } else {
                getOfflineData();
            }
        }

        editTextNewTtlAnn=(EditText) v3.findViewById(R.id.newTitleAnnounce);
        editTextNewAnn=(EditText) v3.findViewById(R.id.newAnnounce);
        Button btAdd=(Button) v3.findViewById(R.id.saveTimeline);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                String newTtlAnn=editTextNewTtlAnn.getText().toString();
                String newAnn=editTextNewAnn.getText().toString();
                // add new item to arraylist

                addAnnounce(id_user, id_kelas, newTtlAnn, newAnn);

//                listTimeline.add(new Timeline("" ,"" + newTtlAnn, "" + newAnn,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//                // notify listview of data changed
//                tlAdapter.notifyDataSetChanged();
            }

        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                timelineList.clear();
                getData(id_kelas);
            }
        });

        TextView buttonBckTimeline = v3.findViewById(R.id.bckTimeline);
        buttonBckTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v3;
    }

    private void addAnnounce(final String id_user, final String id_kelas, final String newTtlAnn, final String newAnn) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_add, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Daftar Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Daftar!", jObj.toString());

                        Toast.makeText(getActivity().getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        String title_notif = "Announce";
                        String message = "Pengumuman Ditambahkan";

                        TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
                        NOTIFICATION_TITLE = title_notif;
                        NOTIFICATION_MESSAGE = message;

                        JSONObject notification = new JSONObject();
                        JSONObject notifcationBody = new JSONObject();
                        try {
                            notifcationBody.put("title", NOTIFICATION_TITLE);
                            notifcationBody.put("message", NOTIFICATION_MESSAGE);

                            notification.put("to", TOPIC);
                            notification.put("data", notifcationBody);
                        } catch (JSONException e) {
                            Log.e(TAG, "onCreate: " + e.getMessage() );
                            pDialog.dismiss();
                        }
                        sendNotification(notification);

                        editTextNewTtlAnn.setText("");
                        editTextNewAnn.setText("");
//                        editTextNewNIM.setText("");

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_user", id_user);
                params.put("id_kelas", id_kelas);
                params.put("title", newTtlAnn);
                params.put("announce", newAnn);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void getData(final String id_kelas) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url_get + "?id_kelas=" + id_kelas, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        DbHelper dbHelper = new DbHelper(getActivity().getApplicationContext());
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        JSONObject jsonObject = response.getJSONObject(i);

                        Timeline timeline = new Timeline();
                        timeline.setId(jsonObject.getInt("id"));
                        timeline.setNama_user(jsonObject.getString("nama_user"));
                        timeline.setTitle(jsonObject.getString("title"));
                        timeline.setAnnounce(jsonObject.getString("announce"));
                        timeline.setPhoto(jsonObject.getString("photo"));

                        timelineList.add(timeline);

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(BaseColumns._ID, jsonObject.getInt("id"));
                        contentValues.put(DbContract.TimelineEntry.COLUMN_NAMA_USER, jsonObject.getString("nama"));
                        contentValues.put(DbContract.TimelineEntry.COLUMN_TITLE, jsonObject.getString("title"));
                        contentValues.put(DbContract.TimelineEntry.COLUMN_ANNOUNCE, jsonObject.getString("announce"));

                        try {
                            db.insertOrThrow(DbContract.TimelineEntry.TABLE_NAME, null, contentValues);
                        } catch (SQLiteConstraintException error) {
                            pDialog.dismiss();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        pDialog.dismiss();
                    }
                }
//                getOfflineData();
                adapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                pDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    private void getOfflineData() {
        DbHelper dbHelper = new DbHelper(getActivity().getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DbContract.TimelineEntry.COLUMN_NAMA_USER,
                DbContract.TimelineEntry.COLUMN_TITLE,
                DbContract.TimelineEntry.COLUMN_ANNOUNCE
        };

        Cursor cursor = db.query(DbContract.TimelineEntry.TABLE_NAME,projection,null,null,null,null,null);

        while (cursor.moveToNext()){
            Timeline timeline = new Timeline();
            timeline.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
            timeline.setNama_user(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.TimelineEntry.COLUMN_NAMA_USER)));
            timeline.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.TimelineEntry.COLUMN_TITLE)));
            timeline.setAnnounce(cursor.getColumnName(cursor.getColumnIndexOrThrow(DbContract.TimelineEntry.COLUMN_ANNOUNCE)));

            timelineList.add(timeline);
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG_NOTIF, "onResponse: " + response.toString());
//                        edtTitle.setText("");
//                        edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG_NOTIF, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onRefresh() {

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        listTimeline = new ArrayList<>();
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Jadwal Pengganti","Kuliah hari senin besok diganti ke hari rabu jam 10.00 WITA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Tugas Tambahan","Kuliah hari senin besok Saya tidak bisa hadir harap untuk mengecek tugas di Assignment","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Kuliah Tambahan","Kuliah tambahan diadakan pada jumat jam 10.00 WITA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//        listTimeline.add(new Timeline("Anak Agung Ketut Agung Cahyawan Wiranatha, ST, MT","Kuliah di Liburkan","Kuliah hari senin besok ditiadakan dan untuk korti mohon untuk mencari jadwal pengganti","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwvrRHleqfyChlwZVwlDTvFQOKM1J14WiBJ304R4bnRsYya8p1zA"));
//    }

}
