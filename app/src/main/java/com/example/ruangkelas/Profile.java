package com.example.ruangkelas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        Button btnedit = findViewById(R.id.btnedit);

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
