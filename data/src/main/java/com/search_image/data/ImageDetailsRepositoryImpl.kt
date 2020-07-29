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


    override suspend fun getComments(
        imageId: String,
        page: Int
    ): Pair<List<CommentResponse>, Boolean> {
        if (page == 1) {
            allComments.clear()
        }

        val resultFromDB: Collection<CommentResponse> = imageDb.commentDao()?.getComments(
            imageId,
            PAGE_SIZE,
            (page - 1) * PAGE_SIZE
        ) ?: emptyList()
        allComments.addAll(
            resultFromDB
        )
        return Pair(allComments, resultFromDB.isEmpty())


    }

    override suspend fun postComments(postCommentRequestDraft: PostCommentRequest):CommentResponse {
        imageDb.commentDao()?.insert(
            Comment(
                imageId = postCommentRequestDraft.imageId,
                comment = postCommentRequestDraft.comment,
                timeStamp = System.currentTimeMillis()
            )
        )
        //fetch last comment only
        val comments = imageDb.commentDao()?.getComments(postCommentRequestDraft.imageId, 1, 0) ?: emptyList()

        allComments.addAll(0,comments)
        return comments[0]
    }
}