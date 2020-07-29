package com.search_image.domain.search

interface ImageDetailsRepository {
    suspend fun getComments(imageId: String,page:Int): List<CommentResponse>
    suspend fun postComments(postCommentRequestDraft: PostCommentRequest)
}