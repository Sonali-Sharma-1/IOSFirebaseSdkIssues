package com.example.firebasesdkproject.database

import androidx.room.*
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.IssueModel
import com.example.firebasesdkproject.model.IssueModelItem

@Dao
interface IssueDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssueList(list: IssueModel?)

    @Query("SELECT * FROM IssueModelItem")
    fun getAllIssuesListing(): List<IssueModelItem>?

    @Query("SELECT * FROM IssueModelItem WHERE issue_id = :id")
    fun getCommentsForIssueId(id: Int): List<IssueModelItem>

    @Query("UPDATE IssueModelItem SET comments_data = :comments WHERE issue_id = :id")
    fun updateIssueWithComments(comments: List<CommentModelItem>, id: Int)

    @Update
    fun update(list: IssueModel)

    @Delete
    fun delete(list: IssueModel)
}