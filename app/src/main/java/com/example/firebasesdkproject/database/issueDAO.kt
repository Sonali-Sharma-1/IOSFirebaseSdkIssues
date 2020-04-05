package com.example.firebasesdkproject.database

import androidx.room.*
import com.example.firebasesdkproject.model.CommentModel
import com.example.firebasesdkproject.model.IssueModel

@Dao
public interface issueDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssueList(list: IssueModel?)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertComments(list: CommentModel?)

    @Delete
    fun deleteIssueList(list: IssueModel)

//    @Query("SELECT * FROM IssueModelItem WHERE number=:userId")
//    fun findCommentsFromTable(userId: Int): List<CommentModel?>?
//
//    @Query("SELECT * FROM IssueModelItem")
//    fun getAllIssuesListing(): List<IssueModel?>?


}