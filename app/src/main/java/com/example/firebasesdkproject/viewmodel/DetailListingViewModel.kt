package com.example.firebasesdkproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasesdkproject.model.CommentListRepository
import com.example.firebasesdkproject.model.CommentModel

class DetailListingViewModel(id: Int?)  : ViewModel(){
    private var commentListRepository: CommentListRepository? = null

    val getCommentList: LiveData<CommentModel>
        get() = commentListRepository!!.getCommentLiveData()

    init {
        commentListRepository = CommentListRepository()
    }


    class Factory(private val id: Int) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailListingViewModel(id) as T
        }

    }


}