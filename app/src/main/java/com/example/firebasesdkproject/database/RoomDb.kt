package com.example.firebasesdkproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.firebasesdkproject.model.IssueModelItem
import com.example.firebasesdkproject.typeConverter.CommentTypeConvertor

@Database(entities = [IssueModelItem::class], version = RoomDb.VERSION,exportSchema = false)
@TypeConverters(CommentTypeConvertor::class)
abstract class RoomDb : RoomDatabase() {

    @SuppressWarnings("WeakerAccess")
    abstract fun issueDao(): IssueDAO
    companion object {
        private var dbInstance: RoomDb ?= null
        const val DB_NAME = "issues.db"
        const val VERSION = 13

        @Synchronized
        internal fun getInstance(context: Context): RoomDb {
            if(dbInstance == null){
                dbInstance = Room
                    .databaseBuilder(context.applicationContext, RoomDb::class.java, DB_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance as RoomDb
        }
    }

}