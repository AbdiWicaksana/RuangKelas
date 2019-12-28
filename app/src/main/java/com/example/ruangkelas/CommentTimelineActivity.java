package com.example.ruangkelas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CommentTimelineActivity extends AppCompatActivity{
    List<Comment> listComment;
    public CommentAdapter comAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_timeline);

        listComment = new ArrayList<>();
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        listComment.add(new Comment("Kelas Pemrograman Mobile","AKLDJALDJALDJALKDJAKLDJAKDJALKDJALKDJALDKJALDJALKDJALKDJALDJALKDJALKDJALKDJALDJAKLDJAL","https://cdn4.iconfinder.com/data/icons/iready-symbols-arrows-vol-1/28/004_009_question_ask_help_support_circle1x-512.png"));
        showComment();
    }

    private void showComment() {
        RecyclerView recyclerView = findViewById(R.id.rec_comment);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        comAdapter = new CommentAdapter(this, listComment);
        recyclerView.setAdapter(comAdapter);
    }
}
