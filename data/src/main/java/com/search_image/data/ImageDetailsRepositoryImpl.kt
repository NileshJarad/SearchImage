package com.search_image.data

import com.search_image.data.storage.Comment
import com.search_image.data.storage.ImageDb
import com.search_image.domain.search.CommentResponse
import com.search_image.domain.search.ImageDetailsRepository
import com.search_image.domain.search.PostCommentRequest
import javax.inject.Inject

class ImageDetailsRepositoryImpl @Inject constructor(
    private val imageDb: ImageDb
) : ImageDetailsRepository {
    override suspend fun getComments(): List<CommentResponse> {
        TODO()
    }

    override suspend fun postComments(postCommentRequestDraft: PostCommentRequest) {
        imageDb.commentDao()?.insert(
            Comment(
                imageId = postCommentRequestDraft.imageId,
                comment = postCommentRequestDraft.comment,
                timeStamp = System.currentTimeMillis()
            )
        )
    }
}