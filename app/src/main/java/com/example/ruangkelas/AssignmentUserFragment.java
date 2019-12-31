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
import com.example.ruangkelas.data.Assignment;
import com.example.ruangkelas.database.DbContract;
import com.example.ruangkelas.database.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentUserFragment extends Fragment {
    View v2;
//    RecyclerView recyclerView;
//    List<Assigment> listAssigment;
//    private AssignmentUserAdapter assignAdapter;
    EditText editTextNewAssign;
    EditText editTextNewDateAssign;
    EditText editTextNewDtlAssign;

    ProgressDialog pDialog;
    String id_user;
    SharedPreferences sharedpreferences;
    int success;

    ConnectivityManager conMgr;

    private RecyclerView aList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Assignment> assignmentList;
    private RecyclerView.Adapter adapter;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAsZXkyqw:APA91bEo1lHbreDQqETM2MsovBtTD0-bRQFeiipIzwri1z-L2IAQxA_wW7AgyxPLYgJd2669boSb_jpKKcqp41ifPzzVMavBQcysRxdGRruGZyQ_i1T35CcZ1pFdp-XBbxFEZXR9cpP0";
    final private String contentType = "application/json";
    final String TAG_NOTIF = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    private static final String url_add = Server.URL + "add_assignment.php";
    private static final String url_get = Server.URL + "get_assignment.php";
    private static final String TAG = HomeActivityAdmin.class.getSimpleName();

    public static final String TAG_ID = "id";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public AssignmentUserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v2 = inflater.inflate(R.layout.fragment_assignment_user,container,false);
//        recyclerView = (RecyclerView) v2.findViewById(R.id.rec_assigment_user);
//        assignAdapter = new AssignmentUserAdapter(getContext(), listAssigment);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(assignAdapter);

        aList = v2.findViewById(R.id.rec_assigment_user);

        assignmentList = new ArrayList<>();
        adapter = new AssignmentUserAdapter(getActivity(),assignmentList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(aList.getContext(), linearLayoutManager.getOrientation());

        aList.setHasFixedSize(true);
        aList.setLayoutManager(linearLayoutManager);
        aList.addItemDecoration(dividerItemDecoration);
        aList.setAdapter(adapter);

        editTextNewAssign=(EditText) v2.findViewById(R.id.namaTugas);
        editTextNewDateAssign=(EditText) v2.findViewById(R.id.tanggalTugas);
        editTextNewDtlAssign=(EditText) v2.findViewById(R.id.detailTugas);


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

        TextView buttonBckAssign = v2.findViewById(R.id.bckAssign);
        buttonBckAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v2;
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

                        Assignment assignment = new Assignment();
                        assignment.setId(jsonObject.getInt("id"));
                        assignment.setNama_assignment(jsonObject.getString("nama_assignment"));
                        assignment.setDetail_assignment(jsonObject.getString("detail_assignment"));
                        assignment.setDate_assignment(jsonObject.getString("date_assignment"));
                        assignment.setPhoto(jsonObject.getString("photo"));

                        assignmentList.add(assignment);

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(BaseColumns._ID, jsonObject.getInt("id"));
                        contentValues.put(DbContract.AssignmentEntry.COLUMN_NAMA_ASSIGNMENT, jsonObject.getString("nama_assignment"));
                        contentValues.put(DbContract.AssignmentEntry.COLUMN_DETAIL_ASSIGNMENT, jsonObject.getString("detail_assignment"));
                        contentValues.put(DbContract.AssignmentEntry.COLUMN_DATE_ASSIGNMENT, jsonObject.getString("date_assignment"));

                        try {
                            db.insertOrThrow(DbContract.AssignmentEntry.TABLE_NAME, null, contentValues);
                        } catch (SQLiteConstraintException error) {
                            //
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
                DbContract.AssignmentEntry.COLUMN_NAMA_ASSIGNMENT,
                DbContract.AssignmentEntry.COLUMN_DETAIL_ASSIGNMENT,
                DbContract.AssignmentEntry.COLUMN_DATE_ASSIGNMENT
        };

        Cursor cursor = db.query(DbContract.AssignmentEntry.TABLE_NAME,projection,null,null,null,null,null);

        while (cursor.moveToNext()){
            Assignment assignment = new Assignment();
            assignment.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
            assignment.setNama_assignment(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.AssignmentEntry.COLUMN_NAMA_ASSIGNMENT)));
            assignment.setDetail_assignment(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.AssignmentEntry.COLUMN_DETAIL_ASSIGNMENT)));
            assignment.setDate_assignment(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.AssignmentEntry.COLUMN_DATE_ASSIGNMENT)));

            assignmentList.add(assignment);
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

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        listAssigment = new ArrayList<>();
//        listAssigment.add(new Assigment("Tugas Pertemuan 1","01 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program Hello World sederhana, tugas dikumpul ke email saya dengan kode 6YH-123-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 2","08 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah Layout pada Android Studio menggunakan Relative Layout, tugas dikumpul ke email saya dengan kode 6YH-124-NIM"));
//        listAssigment.add(new Assigment("Tugas Tambahan","15 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah Desain pada Android Studio yang mengimplementasikan Relative Layout, tugas dikumpul ke email saya dengan kode 6YH-125-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 3","22 Maret 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program sederhana yang mengimplementasikan pengguanaan Constraint Layout, tugas dikumpul ke email saya dengan kode 6YH-126-NIM"));
//        listAssigment.add(new Assigment("Tugas Pengganti UTS","2 April 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program dengan menggunakan RecyclerView dengan tampilan mode grid, list, card view, tugas dikumpul ke email saya dengan kode 6YH-127-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 5","9 April 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program dengan menggunakan dua recycler view dalam satu page /layout, tugas dikumpul ke email saya dengan kode 6YH-128-NIM"));
//        listAssigment.add(new Assigment("Tugas Pertemuan 6","25 April 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah program sederhana dengan menggunakan fragment, tugas dikumpul ke email saya dengan kode 6YH-129-NIM"));
//        listAssigment.add(new Assigment("Tugas Mockup Tugas Besar","1 Mei 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah mockup dari ide yang anda kemukakan dalam project tugas besar, tugas dikumpul ke email saya dengan kode 6YH-130-NIM"));
//        listAssigment.add(new Assigment("Tugas Laporan Progress Tugas Besar","6 Mei 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Buatlah Laporan progress dari tugas besar yang kelompok anda kerjakan, tugas dikumpul ke email saya dengan kode 6YH-131-NIM"));
//        listAssigment.add(new Assigment("Tugas Laporan Tugas Besar","18 Mei 2019","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png","Kirimkan Laporan Project tugas besar yang kelompok anda kerjakan, tugas dikumpul ke email saya dengan kode 6YH-132-NIM"));
//    }
}
