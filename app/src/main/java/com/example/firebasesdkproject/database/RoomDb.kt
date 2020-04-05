package com.example.firebasesdkproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebasesdkproject.core.App
import com.example.firebasesdkproject.model.CommentModelItem
import com.example.firebasesdkproject.model.IssueModelItem

@Database(entities = [IssueModelItem::class, CommentModelItem::class], version = RoomDb.VERSION)
abstract class RoomDb : RoomDatabase() {
    abstract fun issueDao(): issueDAO

    companion object {
        const val DB_NAME = "issues.db"
        const val VERSION = 7
        private val instance: RoomDb by lazy { create(App.instance) }

        @Synchronized
        internal fun getInstance(): RoomDb {
            return instance
        }

        private fun create(context: Context): RoomDb {
            return Room.databaseBuilder(context, RoomDb::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}