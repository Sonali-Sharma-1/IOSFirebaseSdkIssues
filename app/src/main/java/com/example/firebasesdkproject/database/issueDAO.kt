package com.example.firebasesdkproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.firebasesdkproject.model.IssueModel

@Dao
public interface issueDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssueList(list: IssueModel?)

    @Delete
    fun deleteIssueList(list: IssueModel) : Int

}