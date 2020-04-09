package com.example.firebasesdkproject.model

import android.content.ContentValues
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebasesdkproject.Retrofit.RetrofitClient
import com.example.firebasesdkproject.core.App
import com.example.firebasesdkproject.database.RoomDb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListRepository(val roomDb: RoomDb = RoomDb.getInstance(context = App.instance.applicationContext)) {
    var url: String?= null
    var id : Int?= 0
    private var commentList: List<CommentModelItem> = ArrayList()
    private val mutableLiveData = MutableLiveData<List<CommentModelItem>>()

    constructor(commentURL: String?, id: Int?) : this() {
        this.url = commentURL
        this.id = id
    }

    fun getCommentLiveData(): MutableLiveData<List<CommentModelItem>> {
        val service = RetrofitClient.service
        val call = service.getCommentList(url)
        call?.enqueue(object : Callback<List<CommentModelItem>> {

            override fun onResponse(call: Call<List<CommentModelItem>>, response: Response<List<CommentModelItem>>) {
                    commentList = response.body()!!
                    mutableLiveData.value = response.body()
                        saveInDB(commentList)
                    }

            override fun onFailure(call: Call<List<CommentModelItem>>, t: Throwable) {}
        })

        return mutableLiveData
    }

    private fun saveInDB(results: List<CommentModelItem>) {
        try {
            roomDb.issueDao().updateIssueWithComments(results, id!!)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}