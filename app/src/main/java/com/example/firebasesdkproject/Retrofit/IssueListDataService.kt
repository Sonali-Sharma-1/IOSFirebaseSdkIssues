package com.example.firebasesdkproject.Retrofit

import com.example.firebasesdkproject.model.IssueModel
import com.example.firebasesdkproject.model.IssueModelItem
import retrofit2.Call
import retrofit2.http.GET


public interface IssueListDataService {
    @GET("issues")
    fun getIssueList(): Call<IssueModel>
}