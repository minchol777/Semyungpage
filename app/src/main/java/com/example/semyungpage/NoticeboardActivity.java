package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard);
    }

    public void noticeToProfileButton(View view) {
        Intent intent = new Intent(NoticeboardActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void noticeToAnonyButton(View view) {
        Intent intent = new Intent(NoticeboardActivity.this, AnonyActivity.class);
        startActivity(intent);
    }
}