package com.search_image.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class ImageDb : RoomDatabase() {
    abstract fun commentDao(): CommentDao?
}

