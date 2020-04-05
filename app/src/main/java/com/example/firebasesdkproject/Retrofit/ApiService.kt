package com.example.firebasesdkproject.Retrofit

import com.example.firebasesdkproject.model.CommentModel
import com.example.firebasesdkproject.model.IssueModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


public interface ApiService {
    @GET("issues")
    fun getIssueList(): Call<IssueModel>

    @GET
    fun getCommentList(@Url url: String?): Call<CommentModel?>?

}