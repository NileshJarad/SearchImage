package com.search_image.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    var commentId: Long = 0,

    @ColumnInfo(name = "imageId")
    var imageId: String? = null,

    @ColumnInfo(name = "comment")
    var comment: String? = null,

    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long = 0
)