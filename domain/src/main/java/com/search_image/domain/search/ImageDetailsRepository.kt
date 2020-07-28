package com.search_image.domain.search

interface ImageDetailsRepository {
    suspend fun getComments(): List<CommentResponse>
    suspend fun postComments(postCommentRequestDraft: PostCommentRequest)
}