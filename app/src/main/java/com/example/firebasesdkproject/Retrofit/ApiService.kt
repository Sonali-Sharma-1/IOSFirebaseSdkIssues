package com.example.firebasesdkproject.Retrofit

import com.example.firebasesdkproject.model.CommentModel
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.IssueModel
import com.example.firebasesdkproject.model.IssueModelItem
import retrofit2.Call
import retrofit2.http.GET


public interface ApiService {
    @GET("issues")
    fun getIssueList(): Call<IssueModel>

    @GET("{id}/comments")
    fun getCommentList() : Call<CommentModel>
}