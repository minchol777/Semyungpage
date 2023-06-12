package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semyungpage.DTO.CommentsDTO;
import com.example.semyungpage.PostInterface.ComentApiService;
import com.example.semyungpage.PostInterface.PostApiService;
import com.example.semyungpage.adapter.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddComments extends AppCompatActivity {
    int postId =-1;
    EditText editText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);
        postId = getIntent().getIntExtra("postId", -1);
        editText = findViewById(R.id.editCommentMain);
    }



    public void addComments(View view) {
        if (editText.getText().toString().length()<5){
            Toast.makeText(getApplicationContext(), "글자수가 너무 적습니다", Toast.LENGTH_SHORT).show();
        }else{
            String main = editText.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Server.getSeverurl()) // 여기에 실제 서버 주소를 넣어주세요
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ComentApiService comentApiService = retrofit.create(ComentApiService.class);

            CommentsDTO commentsDTO = new CommentsDTO();
            commentsDTO.setMain(main);
            commentsDTO.setPostid(postId);
            commentsDTO.setUserid(1);
            Call<CommentsDTO> call = comentApiService.addComment(postId,commentsDTO);

            call.enqueue(new Callback<CommentsDTO>() {
                @Override
                public void onResponse(Call<CommentsDTO> call, Response<CommentsDTO> response) {
                    if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"성공!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddComments.this, SeePostActivity.class);
                    intent.putExtra("postId",postId);
                    startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"에러 수신: " + response.errorBody(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommentsDTO> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(),"성공!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddComments.this, SeePostActivity.class);
                    intent.putExtra("postId",postId);
                    startActivity(intent);
                }
            });


        }

    }
}
