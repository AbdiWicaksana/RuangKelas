package com.example.ruangkelas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ruangkelas.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.ruangkelas.Login.my_shared_preferences;

public class EditProfile extends AppCompatActivity {

    int success;
    ProgressDialog pDialog;
    String id;
    Button btn_save;
    Intent intent;
    EditText txt_nama, txt_nim, txt_email, txt_username, txt_password;

    SharedPreferences sharedpreferences;

    private static final String TAG = EditProfile.class.getSimpleName();

    private static String url_edit       = Server.URL + "edit_profile.php";
    private static String url_update     = Server.URL + "update_profile.php";

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
        setContentView(R.layout.activity_edit_profile);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);

        txt_nama = findViewById(R.id.txt_nama);
        txt_nim = findViewById(R.id.txt_nim);
        txt_email = findViewById(R.id.txt_email);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btn_save = findViewById(R.id.btn_save);

        checkEdit(id);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = txt_nama.getText().toString();
                String nim = txt_nim.getText().toString();
                String email = txt_email.getText().toString();
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                checkUpdate(id, nama, nim, email, username, password);
            }
        });

    }

    private void checkEdit(final String id){
        pDialog = new ProgressDialog(EditProfile.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response" + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String id = (jObj.getString(TAG_ID));
                        String nama = (jObj.getString(TAG_NAMA));
                        String nim = (jObj.getString(TAG_NIM));
                        String email = (jObj.getString(TAG_EMAIL));
                        String username = (jObj.getString(TAG_USERNAME));
                        String password = (jObj.getString(TAG_PASSWORD));

                        if (!id.isEmpty()) {
                            txt_nama.setText(nama);
                            txt_nim.setText(nim);
                            txt_email.setText(email);
                            txt_username.setText(username);
                            txt_password.setText(password);

                        } else {
                            Toast.makeText(EditProfile.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(EditProfile.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(EditProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void checkUpdate(final String id, final String nama, final String nim, final String email, final String username, final String password){
        pDialog = new ProgressDialog(EditProfile.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        Log.e("Successfully Register!", jObj.toString());
                        String nama = (jObj.getString(TAG_NAMA));
                        String nim = (jObj.getString(TAG_NIM));

                        Toast.makeText(EditProfile.this.getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(TAG_NAMA, nama);
                        editor.putString(TAG_NIM, nim);
                        editor.commit();

                        intent = new Intent(EditProfile.this, Profile.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_USERNAME, username);
                        intent.putExtra(TAG_NAMA, nama);
                        intent.putExtra(TAG_NIM, nim);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(EditProfile.this.getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(EditProfile.this.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("nama", nama);
                params.put("nim", nim);
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);

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
