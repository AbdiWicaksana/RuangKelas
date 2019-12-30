package com.example.ruangkelas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruangkelas.data.Member;

import java.util.List;

public class MemberUserAdapter extends RecyclerView.Adapter<MemberUserAdapter.ViewHolder> {

    private Context context;
    private List<Member> list;

    public MemberUserAdapter(Context context, List<Member> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Member member = list.get(position);

        holder.textNama.setText(String.valueOf(member.getNama()));
        holder.textNIM.setText(String.valueOf(member.getNim()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNama, textNIM;
        public ImageView image_view;

        public ViewHolder(View itemView) {
            super(itemView);

            image_view = itemView.findViewById(R.id.image_view_user);
            textNama = itemView.findViewById(R.id.namaMemberUser);
            textNIM = itemView.findViewById(R.id.nimMemberUser);
        }
    }

}