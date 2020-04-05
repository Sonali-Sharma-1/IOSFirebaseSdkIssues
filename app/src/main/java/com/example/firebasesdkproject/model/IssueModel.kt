package com.example.firebasesdkproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class IssueModel : ArrayList<IssueModelItem>()

@Entity
data class IssueModelItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val number : Int,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Description")
    val body: String,
    @ColumnInfo(name = "Comments URL")
    val comments_url : String
)
