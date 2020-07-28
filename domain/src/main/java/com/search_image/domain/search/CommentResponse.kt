package com.search_image.domain.search

data class CommentResponse(
    val comment: String,
    val commentId: Long,
    val imageId: String,
    val timeStamp: Long
)