package com.search_image.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.search_image.domain.search.CommentResponse

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comment: Comment?)

    @Query("Select * from comment_table WHERE imageId = :imageId ORDER BY timeStamp DESC LIMIT :limit OFFSET :offset")
    fun getComments(imageId: String,limit :Int, offset: Int): List<CommentResponse>
}