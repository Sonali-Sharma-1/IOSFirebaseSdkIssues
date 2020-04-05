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

class CommentListRepository(val roomDb: RoomDb = RoomDb.getInstance()) {
    var url: String?= null
    var id : Int?= 0
    private var commentList: CommentModel? = null
    private val mutableLiveData = MutableLiveData<CommentModel>()

    constructor(commentURL: String?, id: Int?) : this() {
        this.url = commentURL
        this.id = id
    }

    fun getCommentLiveData(): MutableLiveData<CommentModel> {
        val service = RetrofitClient.service
        val call = service.getCommentList(url)
        var item : CommentModelItem ?= null
        item?.issueId = id
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
        Log.d(ContentValues.TAG, "${results} Comments inserted to database")
        try {
            roomDb.issueDao().insertComments(results)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}