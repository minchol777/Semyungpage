package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semyungpage.DTO.PostDTO;
import com.example.semyungpage.PostInterface.PostApiService;
import com.example.semyungpage.adapter.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPostActivity extends AppCompatActivity {
    int boardId=-1;
    EditText main;
    EditText title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post);
        title = findViewById(R.id.editTitle);
        main = findViewById(R.id.editmain);
        boardId = getIntent().getIntExtra("boardId", -1);

    }

    public void addPost(View view) {
        if (title.getText().toString().length()<2 || main.getText().toString().length()<2){
            Toast.makeText(getApplicationContext(), "글자수가 너무 적습니다", Toast.LENGTH_SHORT).show();
        }else{
            String t = title.getText().toString();
            String m = main.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Server.getSeverurl()) // 여기에 실제 서버 주소를 넣어주세요
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PostApiService postApiService = retrofit.create(PostApiService.class);
            PostDTO postDTO = new PostDTO();
            postDTO.setMain(m);
            postDTO.setUserid(1);
            postDTO.setBoardid(boardId);
            postDTO.setTitle(t);

            Call<PostDTO> call = postApiService.createPost(postDTO);

            call.enqueue(new Callback<PostDTO>() {
                @Override
                public void onResponse(Call<PostDTO> call, Response<PostDTO> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(AddPostActivity.this, "등록 성공!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddPostActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AddPostActivity.this, "서버 에러:"+response.errorBody(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostDTO> call, Throwable t) {
                    Toast.makeText(AddPostActivity.this, "등록 성공 하였습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPostActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            });

        }
    }
}
