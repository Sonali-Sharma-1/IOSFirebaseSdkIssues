package com.example.firebasesdkproject.typeConverter

import androidx.room.TypeConverter
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class CommentTypeConvertor {
    @TypeConverter
    fun fromCommentList(commentList: List<CommentModelItem>?): String? {
        if (commentList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<CommentModelItem>>() {}.type
        return gson.toJson(commentList, type)
    }

    @TypeConverter
    fun toCommentList(commentListString: String?): List<CommentModelItem>? {
        if (commentListString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<CommentModelItem>>() {}.type
        return gson.fromJson<List<CommentModelItem>>(commentListString, type)
    }
}