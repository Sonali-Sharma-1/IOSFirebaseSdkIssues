package com.example.firebasesdkproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.firebasesdkproject.typeConverter.CommentTypeConvertor

class IssueModel : ArrayList<IssueModelItem>()

@Entity
data class IssueModelItem(
    @PrimaryKey
    @ColumnInfo(name = "issue_id")
    val number: Int,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Description")
    val body: String,
    @ColumnInfo(name = "Comments URL")
    val comments_url: String,
    @TypeConverters(CommentTypeConvertor::class)
    @ColumnInfo(name = "comments_data")
    val commentsList: List<CommentModelItem>?)
