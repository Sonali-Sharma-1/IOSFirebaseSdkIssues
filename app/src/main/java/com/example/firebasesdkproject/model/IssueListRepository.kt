package com.example.firebasesdkproject.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebasesdkproject.Retrofit.RetrofitClient
import com.example.firebasesdkproject.database.RoomDb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class IssueListRepository(val roomDb: RoomDb = RoomDb.getInstance()) {
    private var list: IssueModel? = null
    private val mutableLiveData = MutableLiveData<IssueModel>()


    fun getMutableLiveData(): MutableLiveData<IssueModel> {

        val service = RetrofitClient.service

        val call = service.getIssueList()

        call!!.enqueue(object : Callback<IssueModel> {
            override fun onResponse(call: Call<IssueModel>, response: Response<IssueModel>) {
                list = response.body()!!
                mutableLiveData.value = response.body()
                saveInDB(response.body())
            }

            override fun onFailure(call: Call<IssueModel>, t: Throwable) {}
        })
        return mutableLiveData
    }

    private fun saveInDB(results: IssueModel?) {
        Log.d(TAG, "${results} inserted to databade")
        roomDb.issueDao().insertIssueList(results)
    }
}
