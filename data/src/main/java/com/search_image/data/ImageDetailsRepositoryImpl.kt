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

    private var allComments = mutableListOf<CommentResponse>()

    companion object {
        const val PAGE_SIZE = 10
    }


    override suspend fun getComments(imageId: String, page: Int): List<CommentResponse> {
        if (page == 1) {
            allComments.clear()
        }
        allComments.addAll(
            imageDb.commentDao()?.getAllComments(
                imageId,
                PAGE_SIZE,
                (page - 1) * PAGE_SIZE
            ) ?: emptyList()
        )
        return allComments


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