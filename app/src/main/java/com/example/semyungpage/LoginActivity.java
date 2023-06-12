package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loginButton(View view) {
        Intent intent = new Intent(LoginActivity.this, NoticeboardActivity.class);
        startActivity(intent);
    }
}