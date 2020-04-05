package com.example.firebasesdkproject.model

import android.content.ContentValues
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebasesdkproject.Retrofit.RetrofitClient
import com.example.firebasesdkproject.database.RoomDb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentListRepository(val roomDb: RoomDb = RoomDb.getInstance()) {
    var url: String? = null
    private var commentList: CommentModel? = null
    private val mutableLiveData = MutableLiveData<CommentModel>()

    constructor(commentURL: String?) : this() {
        this.url = commentURL
    }

    fun getCommentLiveData(): MutableLiveData<CommentModel> {
        val service = RetrofitClient.service
        val call = service.getCommentList(url)
        call?.enqueue(object : Callback<CommentModel?> {

            override fun onResponse(call: Call<CommentModel?>, response: Response<CommentModel?>) {
                commentList = response.body()!!
                mutableLiveData.value = response.body()
                AsyncTask.execute {
                    saveInDB(response.body())
                }
            }

            override fun onFailure(call: Call<CommentModel?>, t: Throwable) {}
        })

        return mutableLiveData
    }

    private fun saveInDB(results: CommentModel?) {
        Log.d(ContentValues.TAG, "${results} inserted to databade")
        roomDb.issueDao().insertComments(results)
    }
}