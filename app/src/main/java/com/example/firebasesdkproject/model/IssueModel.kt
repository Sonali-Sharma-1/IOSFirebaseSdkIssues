package com.example.firebasesdkproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class IssueModel : ArrayList<IssueModelItem>()

@Entity
data class IssueModelItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val body: String,
    val title: String,
    val comments_url : String
)
