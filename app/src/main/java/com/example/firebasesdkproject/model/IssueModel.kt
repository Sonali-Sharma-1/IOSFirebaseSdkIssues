package com.example.firebasesdkproject.model

class IssueModel : ArrayList<IssueModelItem>()

data class IssueModelItem(val body: String, val id: Int, val title: String)
