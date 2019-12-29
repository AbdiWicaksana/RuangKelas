package com.example.ruangkelas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruangkelas.data.Kelas;

import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {

    private Context context;
    private List<Kelas> list;

    public static final String TAG_ID_KELAS       = "id_kelas";

    public KelasAdapter(Context context, List<Kelas> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kelas kelas = list.get(position);

        final String id_kelas = kelas.getId().toString();

        holder.textNamaKelas.setText(kelas.getNama_kelas());
        holder.textSubjectKelas.setText(String.valueOf(kelas.getSubject_kelas()));
        holder.textAuthorKelas.setText(String.valueOf(kelas.getAuthor_kelas()));

        holder.textNamaKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_kelas", id_kelas);
//                Toast.makeText(context, id_kelas, Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNamaKelas, textSubjectKelas, textAuthorKelas;

        public ViewHolder(View itemView) {
            super(itemView);

            textNamaKelas = itemView.findViewById(R.id.namaClasses);
            textSubjectKelas = itemView.findViewById(R.id.subjectClasses);
            textAuthorKelas = itemView.findViewById(R.id.authorClasses);
        }
    }

}