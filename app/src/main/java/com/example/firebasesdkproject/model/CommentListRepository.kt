package com.example.firebasesdkproject.model

import androidx.lifecycle.MutableLiveData
import com.example.firebasesdkproject.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListRepository {
    private var commentList: CommentModel ?= null
    private val mutableLiveData = MutableLiveData<CommentModel>()


    fun getCommentLiveData(): MutableLiveData<CommentModel> {

        val service = RetrofitClient.service

        val call = service.getCommentList()

        call.enqueue(object : Callback<CommentModel> {
            override fun onResponse(call: Call<CommentModel>, response: Response<CommentModel>) {
                commentList = response.body()!!
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<CommentModel>, t: Throwable) {}
        })
        return mutableLiveData
    }
}