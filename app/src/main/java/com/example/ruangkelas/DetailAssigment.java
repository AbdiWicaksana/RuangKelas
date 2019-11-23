package com.example.ruangkelas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailAssigment extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_assigment);

        getIncomingIntent();

        TextView buttonBckDetailAssign = findViewById(R.id.bckDtlAssign);
        buttonBckDetailAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("link") && getIntent().hasExtra("nama") && getIntent().hasExtra("date") &&  getIntent().hasExtra("detail")){

            String Link = getIntent().getStringExtra("link");
            String Nama = getIntent().getStringExtra("nama");
            String Date = getIntent().getStringExtra("date");
            String Detail = getIntent().getStringExtra("detail");
            setData(Link,Nama,Date,Detail);
        }
    }

    private void setData(String Link, String Nama, String Date, String Detail){
        TextView namaDetail = findViewById(R.id.namaTgs);
        namaDetail.setText(Nama);
        TextView posisiDetail = findViewById(R.id.dateTgs);
        posisiDetail.setText(Date);
        TextView det = findViewById(R.id.judulDetailTgs);
        det.setText(Detail);

        ImageView gambarDetail = findViewById(R.id.imgTugas);
        Glide.with(this)
                .asBitmap()
                .load(Link)
                .into(gambarDetail);
    }
}
