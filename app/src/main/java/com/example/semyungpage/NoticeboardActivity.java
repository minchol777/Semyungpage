package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semyungpage.DTO.PostTitleResponseDTO;
import com.example.semyungpage.PostInterface.PostApiService;
import com.example.semyungpage.adapter.CustomArrayAdapter;
import com.example.semyungpage.adapter.Server;

import java.util.ArrayList;
import java.util.List;

public class NoticeboardActivity extends AppCompatActivity {

    List<PostTitleResponseDTO> posts = new ArrayList<>(); //이거 않하면 어뎁터 선언에서 널포인터 뜬다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeboard);

        // Retrofit 초기화
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.getSeverurl()) // 여기에 실제 서버 주소를 넣어주세요
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostApiService postApiService = retrofit.create(PostApiService.class);

        //리스트뷰를 가지고오고 어댑터 생성
        ListView listView = findViewById(R.id.noticeListView);
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.borad_view, posts);
        listView.setAdapter(adapter);
        //어뎁터에 온클릭리스너 추가 이거때문에 위에 선언함
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(NoticeboardActivity.this, SeePostActivity.class);
            intent.putExtra("postId", posts.get(position).getId());
            startActivity(intent);
        });

        //api 설정
        Call<List<PostTitleResponseDTO>> call = postApiService.getPostsByBoardId(1); //보드 아이디1 아마?
        // 서버와 통신
        call.enqueue(new Callback<List<PostTitleResponseDTO>>() {
            @Override
            public void onResponse(Call<List<PostTitleResponseDTO>> call, Response<List<PostTitleResponseDTO>> response) {
                if (response.isSuccessful()) {
                        posts.clear(); // 기존 데이터를 지우고
                        posts.addAll(response.body()); // 새로운 데이터를 추가합니다.
                        adapter.notifyDataSetChanged(); // 어뎁터에게 전달 

                } else {
                    //Log.d("MainActivity", "onResponse: " + response.errorBody());
                    Toast.makeText(getApplicationContext(),"에러 수신: " + response.errorBody(),Toast.LENGTH_SHORT).show();
                }
            }
        @Override
        public void onFailure(Call<List<PostTitleResponseDTO>> call, Throwable t) {
            Toast.makeText(getApplicationContext(),"서버연결실패!"+t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });



} //end of oncreate
    public void noticeToProfileButton(View view) {
        Intent intent = new Intent(NoticeboardActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void noticeToAnonyButton(View view) {
        Intent intent = new Intent(NoticeboardActivity.this, AnonyActivity.class);
        startActivity(intent);
    }

}