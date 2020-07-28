package com.search_image.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comment: Comment?)
}