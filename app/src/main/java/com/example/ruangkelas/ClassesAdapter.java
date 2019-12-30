package com.example.ruangkelas;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruangkelas.data.Kelas;
import com.example.ruangkelas.model.kelas;
import com.example.ruangkelas.data.factory.AppDatabase;

import com.bumptech.glide.Glide;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {

    Context context;
    List<Kelas> listKelas;
    private AppDatabase appDatabase;

    public ClassesAdapter(Context context, List<Kelas> listKelas) {
        this.context = context;
        this.listKelas = listKelas;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "id12007477_ruangkelas").allowMainThreadQueries().build();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.home, parent, false);
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

//        holder.kelasRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new AlertDialog.Builder(view.getContext())
//                        .setTitle("Choose Action")
//                        .setMessage("Are you sute want to delete this data ?")
//
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                onDeleteData(position);
//                            }
//                        })
//
//                        .setNegativeButton("No",null)
//                        .show();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listKelas.size();
    }

//    private void onDeleteData(int position){
//        appDatabase.KelasDAO().deleteKelas(listKelas.get(position));
//        listKelas.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, listKelas.size());
//        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kelasNama;
        TextView kelasSubjek;
        TextView kelasAuthor;
        TextView kelasRemove;


        public MyViewHolder(View itemView) {
            super(itemView);

            kelasNama = (TextView) itemView.findViewById(R.id.namaClasses);
            kelasSubjek = (TextView) itemView.findViewById(R.id.subjectClasses);
            kelasAuthor = (TextView) itemView.findViewById(R.id.authorClasses);
            kelasRemove = (TextView) itemView.findViewById(R.id.rmvClass);
        }
    }
}