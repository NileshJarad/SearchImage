package com.search_image.domain.search

data class PostCommentRequest(
    val imageId: String,
    val comment: String
)