package com.example.ruangkelas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);

        final EditText editTextRprt = findViewById(R.id.etreport);
        Button btnRprt = findViewById(R.id.btnReport);

        btnRprt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextRprt.getText().equals("")) {
                    Toast.makeText(getApplicationContext(),"Inputan Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Report Terkirim",Toast.LENGTH_SHORT).show();
                }
            }
        });



        TextView buttonBckReport = findViewById(R.id.bckReport);
        buttonBckReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
