package com.example.semyungpage.PostInterface;


import com.example.semyungpage.DTO.CommentsDTO;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface ComentApiService {
    @GET("/comments/comment/{id}")
    Call<List<CommentsDTO>> getPostComments(@Path("id") int id);

    @POST("/comments/comment/{id}")
    Call<CommentsDTO> addComment(@Path("id") int id, @Body CommentsDTO commentDTO);

    @DELETE("/comments/comment/{id}")
    Call<Void> deleteComment(@Path("id") int id);
}

