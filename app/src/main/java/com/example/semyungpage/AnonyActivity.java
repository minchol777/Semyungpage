package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AnonyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anonyboard);
    }

    public void anoyToProfileButton(View view) {
        Intent intent = new Intent(AnonyActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    public void anoyToNoticeButton(View view) {
        Intent intent = new Intent(AnonyActivity.this,NoticeboardActivity.class);
        startActivity(intent);
    }
}
