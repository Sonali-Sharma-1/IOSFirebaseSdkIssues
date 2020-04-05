package com.example.firebasesdkproject.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import kotlin.collections.ArrayList


class CommentModel : ArrayList<CommentModelItem>()


@Entity(tableName = "CommentModelItem", foreignKeys = [ForeignKey(
    entity = IssueModelItem::class, parentColumns = arrayOf("CommentId"),
    childColumns = arrayOf("comment_id"), onDelete = CASCADE)])

data class CommentModelItem(
    @PrimaryKey
    @ColumnInfo(name="comment_id")
    val id: Int,
    @Embedded
    val user: User,
    @ColumnInfo(name = "Comments")
    val body: String)

data class User(
    @ColumnInfo(name = "Author")
    val login: String
)