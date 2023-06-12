package com.example.semyungpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semyungpage.DTO.PostDTO;
import com.example.semyungpage.DTO.PostTitleResponseDTO;
import com.example.semyungpage.PostInterface.PostApiService;
import com.example.semyungpage.adapter.CustomArrayAdapter;
import com.example.semyungpage.adapter.CustomArrayAdapter2;
import com.example.semyungpage.adapter.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    List<PostDTO> posts = new ArrayList<>();
    ListView listView;
    SearchView editText;
    CustomArrayAdapter2 adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        editText = findViewById(R.id.searchView);
        listView = findViewById(R.id.searchList);
        adapter = new CustomArrayAdapter2(this, R.layout.borad_view, posts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SearchActivity.this, SeePostActivity.class);
            intent.putExtra("postId", posts.get(position).getId());
            startActivity(intent);
        });
    }

    public void Search(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.getSeverurl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostApiService postApiService = retrofit.create(PostApiService.class);

        Call<List<PostDTO>> call = postApiService.getPostsByTitle(editText.getQuery().toString());
        call.enqueue(new Callback<List<PostDTO>>() {
            @Override
            public void onResponse(Call<List<PostDTO>> call, Response<List<PostDTO>> response) {
                if (response.isSuccessful()) {
                    posts.clear(); // 기존 데이터를 지우고
                    posts.addAll(response.body()); // 새로운 데이터를 추가.
                    adapter.notifyDataSetChanged(); // 어뎁터에게 전달

                } else {
                    //Log.d("MainActivity", "onResponse: " + response.errorBody());
                    Toast.makeText(getApplicationContext(),"에러 수신: " + response.errorBody(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"서버연결실패!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }



}
