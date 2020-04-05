package com.example.firebasesdkproject.model

import androidx.room.*

class CommentModel : ArrayList<CommentModelItem>()

@Entity
data class CommentModelItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "Comments")
    val body: String,
    @Embedded
    val user: User)

data class User(
    @ColumnInfo(name = "Author")
    val login: String
)