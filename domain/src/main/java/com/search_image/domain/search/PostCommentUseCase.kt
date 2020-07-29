package com.search_image.domain.search

import javax.inject.Inject

/**
 * post comment use case declaration
 */
interface PostCommentUseCase {
    suspend operator fun invoke(postComment: PostCommentRequest) : CommentResponse
}


/**
 * post comment use case implementation
 *
 * It will take post comment request and add it to the local database
 */
class PostCommentUseCaseImpl @Inject constructor(
    private val imageDetailsRepository: ImageDetailsRepository
) : PostCommentUseCase {
    override suspend fun invoke(postComment: PostCommentRequest) :CommentResponse {
        return imageDetailsRepository.postComments(postComment)
    }
}