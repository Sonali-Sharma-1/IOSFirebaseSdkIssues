package com.example.firebasesdkproject.model

import androidx.lifecycle.MutableLiveData
import com.example.firebasesdkproject.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class IssueListRepository {
    private var list: IssueModel ?= null
    private val mutableLiveData = MutableLiveData<IssueModel>()


    fun getMutableLiveData(): MutableLiveData<IssueModel> {

        val service = RetrofitClient.service

        val call = service.getIssueList()

        call!!.enqueue(object : Callback<IssueModel> {
            override fun onResponse(call: Call<IssueModel>, response: Response<IssueModel>) {
                list = response.body()!!
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<IssueModel>, t: Throwable) {}
        })
        return mutableLiveData
    }
}