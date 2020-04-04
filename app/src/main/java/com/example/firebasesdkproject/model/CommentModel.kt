package com.example.firebasesdkproject.model

class CommentModel : ArrayList<CommentModelItem>()

data class CommentModelItem(val body: String, val id: Int, val user: User)

data class User(val login: String)