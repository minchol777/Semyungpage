package com.example.semyungpage.PostInterface;


import com.myloginpage.a13456.DTO.PostDTO;
import com.myloginpage.a13456.DTO.PostResponseDTO;
import com.myloginpage.a13456.DTO.PostTitleResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostApiService {
        @GET("/posts/board/{boardId}")
        Call<List<PostTitleResponseDTO>> getPostsByBoardId(@Path("boardId") int boardId);

        @POST("/posts/create")
        Call<PostDTO> createPost(@Body PostDTO postDTO);

        @GET("/posts/post/{id}")
        Call<PostResponseDTO> getPost(@Path("id") int id);

        @GET("/posts/search/{title}")
        Call<List<PostDTO>> getPostsByTitle(@Path("title") String title);

        @PUT("/posts/post/{id}")
        Call<PostDTO> updatePost(@Path("id") int id, @Body PostDTO postDTO);

        @DELETE("/posts/post/{id}")
        Call<Void> deletePost(@Path("id") int id);
    }

