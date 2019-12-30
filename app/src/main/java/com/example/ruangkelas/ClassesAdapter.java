package com.example.ruangkelas;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruangkelas.data.Kelas;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {

    Context context;
    List<Kelas> listKelas;

    public ClassesAdapter(Context context, List<Kelas> listKelas) {
        this.context = context;
        this.listKelas = listKelas;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.home_user, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        String getKelasNama = listKelas.get(position).getNamaKelas();
//        String getKelasSubjek = listKelas.get(position).getSubjekKelas();
////        String getKelasAuthor = listKelas.get(position).getAuthorKelas();
//
//        holder.kelasNama.setText(getKelasNama);
//        holder.kelasSubjek.setText(getKelasSubjek);
////        holder.kelasAuthor.setText(getKelasAuthor);
//        holder.kelasNama.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MainActivityUser.class);
//                context.startActivity(intent);
//            }
//        });

        Kelas kelas = listKelas.get(position);

        final String id_kelas = kelas.getId().toString();

        holder.kelasNama.setText(kelas.getNama_kelas());
        holder.kelasSubjek.setText(String.valueOf(kelas.getSubject_kelas()));
        holder.kelasAuthor.setText(String.valueOf(kelas.getAuthor_kelas()));

        holder.kelasNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivityUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_kelas", id_kelas);
//                Toast.makeText(context, id_kelas, Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKelas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kelasNama;
        TextView kelasSubjek;
        TextView kelasAuthor;


        public MyViewHolder(View itemView) {
            super(itemView);

            kelasNama = (TextView) itemView.findViewById(R.id.namaClasses);
            kelasSubjek = (TextView) itemView.findViewById(R.id.subjectClasses);
            kelasAuthor = (TextView) itemView.findViewById(R.id.authorClasses);
        }
    }
}