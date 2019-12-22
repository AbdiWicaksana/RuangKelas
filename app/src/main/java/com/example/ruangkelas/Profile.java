package com.example.ruangkelas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    TextView txt_nama, txt_nim;
    ImageView photo_profile;
    String id, nama, nim;

    public static final String TAG_ID           = "id";
    public static final String TAG_NAMA         = "nama";
    public static final String TAG_NIM          = "nim";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        Button btnedit = findViewById(R.id.btnedit);
        txt_nama = findViewById(R.id.txt_nama);
        txt_nim = findViewById(R.id.txt_nim);
        photo_profile = findViewById(R.id.photo_profile);

        sharedpreferences = Profile.this.getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        nim = sharedpreferences.getString(TAG_NIM, null);

        txt_nama.setText(nama);
        txt_nim.setText(nim);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoEdit = new Intent(Profile.this, EditProfile.class);
                startActivity(gotoEdit);
            }
        });
//        final EditText editTextRprt = findViewById(R.id.etreport);
//        Button btnRprt = findViewById(R.id.btnReport);
//
//        btnRprt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (editTextRprt.getText().equals("")) {
//                    Toast.makeText(getApplicationContext(),"Inputan Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Profile Terkirim",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



        TextView buttonBckReport = findViewById(R.id.bckReport);
        buttonBckReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
