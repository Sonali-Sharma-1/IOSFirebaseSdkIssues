package com.example.firebasesdkproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firebasesdkproject.model.CommentModel
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.IssueModel
import com.example.firebasesdkproject.model.IssueModelItem

@Dao
interface issueDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssueList(list: IssueModel?)

    @Insert
    fun insertComments(list: CommentModel?)

    @Delete
    fun deleteIssueList(list: IssueModel)

    @Query("SELECT * FROM IssueModelItem")
    fun getAllIssuesListing(): LiveData<List<IssueModelItem>>?

    @Query("SELECT * FROM CommentModelItem")
    fun getCommentListing(): LiveData<List<CommentModelItem>>?
}