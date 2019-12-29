package com.example.ruangkelas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruangkelas.data.Member;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private Context context;
    private List<Member> list;

    public MemberAdapter(Context context, List<Member> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Member member = list.get(position);

        holder.textNama.setText(member.getNama());
        holder.textNIM.setText(member.getNim());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNama, textNIM;

        public ViewHolder(View itemView) {
            super(itemView);

            textNama = itemView.findViewById(R.id.namaMember);
            textNIM = itemView.findViewById(R.id.nimMember);
        }
    }

}