package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semyungpage.DTO.CommentsDTO;
import com.example.semyungpage.DTO.PostResponseDTO;
import com.example.semyungpage.DTO.PostTitleResponseDTO;
import com.example.semyungpage.PostInterface.ComentApiService;
import com.example.semyungpage.PostInterface.PostApiService;
import com.example.semyungpage.adapter.CustomArrayAdapter;
import com.example.semyungpage.adapter.CustomCommentsAdapter;
import com.example.semyungpage.adapter.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeePostActivity extends AppCompatActivity {
    int postId =-1;
    List<CommentsDTO> posts = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_post);

        postId = getIntent().getIntExtra("postId", -1);

        // Retrofit 초기화
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.getSeverurl()) // 여기에 실제 서버 주소를 넣어주세요
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostApiService postApiService = retrofit.create(PostApiService.class);
        ComentApiService comentApiService = retrofit.create(ComentApiService.class);

        //TextView 가지고오기
        TextView title = findViewById(R.id.seeTitle);
        TextView main = findViewById(R.id.seeMain);
        //리스트 뷰 들고오기
        ListView listView = findViewById(R.id.postComment);
        CustomCommentsAdapter adapter = new CustomCommentsAdapter(this, R.layout.borad_view, posts);
        listView.setAdapter(adapter);

        //api 선언
        Call<PostResponseDTO> call = postApiService.getPost(postId);

        //서버와 통신 enqueue는 비동기통신
        call.enqueue(new Callback<PostResponseDTO>() {
            @Override
            public void onResponse(Call<PostResponseDTO> call, Response<PostResponseDTO> response) {
                if(response.isSuccessful()){
                    title.setText(response.body().getTitle());
                    main.setText(response.body().getMain());
                }else{
                    Toast.makeText(getApplicationContext(),"에러 수신: " + response.errorBody(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponseDTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"서버연결실패!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<CommentsDTO>> comentcall = comentApiService.getPostComments(postId);
        comentcall.enqueue(new Callback<List<CommentsDTO>>() {
            @Override
            public void onResponse(Call<List<CommentsDTO>> call, Response<List<CommentsDTO>> response) {
                if(response.isSuccessful()){
                    posts.clear(); // 기존 데이터를 지우고
                    posts.addAll(response.body());// 새로운 데이터를 추가합니다.
                    adapter.notifyDataSetChanged(); // 어뎁터에게 전달
                }
            }

            @Override
            public void onFailure(Call<List<CommentsDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"서버연결실패!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void goAddComments(View view) {
        Intent intent = new Intent(SeePostActivity.this, AddComments.class);
        intent.putExtra("postId", postId);
        startActivity(intent);
    }

    public void sis(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.getSeverurl()) // 여기에 실제 서버 주소를 넣어주세요
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostApiService postApi = retrofit.create(PostApiService.class);

        Call<Void> call = postApi.deletePost(postId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "삭제성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SeePostActivity.this,NoticeboardActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "삭제성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SeePostActivity.this,NoticeboardActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "삭제성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeePostActivity.this,NoticeboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
