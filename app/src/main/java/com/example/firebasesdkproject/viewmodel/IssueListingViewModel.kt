package com.example.firebasesdkproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasesdkproject.database.RoomDb
import com.example.firebasesdkproject.model.IssueListRepository
import com.example.firebasesdkproject.model.IssueModel

class IssueListingViewModel : ViewModel() {
    private var issueListRepository: IssueListRepository? = null

    val getAllList: LiveData<IssueModel>
    get() = issueListRepository!!.getMutableLiveData()

    init {
        issueListRepository = IssueListRepository()
    }

}