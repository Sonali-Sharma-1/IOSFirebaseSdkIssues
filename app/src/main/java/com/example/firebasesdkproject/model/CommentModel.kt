package com.example.firebasesdkproject.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import kotlin.collections.ArrayList


class CommentModel : ArrayList<CommentModelItem>()

@Entity(
    tableName = "CommentModelItem", foreignKeys = [ForeignKey(
        entity = IssueModelItem::class, parentColumns = arrayOf("id"),
        childColumns = arrayOf("issue_id"), onDelete = CASCADE
    )]
)

data class CommentModelItem(
    @PrimaryKey
    @ColumnInfo(name="comment_id")
    val id: Int,
    @ColumnInfo(name = "issue_id")
    var issueId : Int?,
    @Embedded
    val user: User,
    @ColumnInfo(name = "Comments")
    val body: String)


data class User(
    @ColumnInfo(name = "Author")
    val login: String
)