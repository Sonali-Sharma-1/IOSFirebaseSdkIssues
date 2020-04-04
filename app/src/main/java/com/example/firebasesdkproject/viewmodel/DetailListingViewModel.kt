package com.example.firebasesdkproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasesdkproject.model.CommentListRepository
import com.example.firebasesdkproject.model.CommentModel

class DetailListingViewModel(commentURL: String?)  : ViewModel(){
    private var commentListRepository: CommentListRepository? = null

    val getCommentList: LiveData<CommentModel>
        get() = commentListRepository!!.getCommentLiveData()

    init {
        commentListRepository = CommentListRepository(commentURL)
    }


    class Factory(private val commentURL: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailListingViewModel(commentURL) as T
        }

    }


}