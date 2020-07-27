package com.search_image.domain.search

import com.search_image.domain.search.common.ApiResponse


/**
 * Search image repository declaration
 */
interface SearchImageRepository {

    /**
     * Takes search query parameter,page count and return search result for images
     *
     * @param searchQuery - search query to search
     * @param pageCount - page count for search images
     */
    suspend fun searchImage(
        searchQuery: String,
        pageCount: Int
    ) : ApiResponse<List<SearchImageResponse>>
}