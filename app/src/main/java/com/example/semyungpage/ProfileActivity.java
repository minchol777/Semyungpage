package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileboard);
    }

    public void profileToNoticeButton(View view) {
        Intent intent = new Intent(this,NoticeboardActivity.class);
        startActivity(intent);
    }

    public void profileToAnoyButton(View view) {
        Intent intent = new Intent(this,AnonyActivity.class);
        startActivity(intent);
    }
}
