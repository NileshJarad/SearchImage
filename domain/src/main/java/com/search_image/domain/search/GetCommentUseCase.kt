package com.search_image.domain.search

import javax.inject.Inject

/**
 * post comment use case declaration
 */
interface GetCommentUseCase {
    suspend operator fun invoke(imageId: String,page :Int): Pair<List<CommentResponse>,Boolean>
}


/**
 * post comment use case implementation
 *
 * It will take post comment request and add it to the local database
 */
class GetCommentUseCaseImpl @Inject constructor(
    private val imageDetailsRepository: ImageDetailsRepository
) : GetCommentUseCase {
    override suspend fun invoke(imageId: String,page : Int): Pair<List<CommentResponse>,Boolean> {
        return imageDetailsRepository.getComments(imageId,page)
    }
}