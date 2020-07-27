package com.search_image.domain.search

import com.search_image.domain.search.common.ApiResponse
import javax.inject.Inject

/**
 * Search image use case declaration
 */
interface SearchImageUseCase {
    suspend operator fun invoke(searchQuery: String, pageCount: Int): ApiResponse<List<SearchImageResponse>>
}

/**
 * Search use case implementation
 *
 * It will take search repository object , calls search image method on repository and return result to calling co-routine
 */
class SearchImageUseCaseImpl @Inject constructor(
    private val searchImageRepository: SearchImageRepository
) : SearchImageUseCase {
    override suspend operator fun invoke(
        searchQuery: String,
        pageCount: Int
    ): ApiResponse<List<SearchImageResponse>> {
        return searchImageRepository.searchImage(searchQuery, pageCount)
    }
}