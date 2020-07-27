package com.search_image.domain.search

/**
 * Domain module for search image result
 */
data class SearchImageResponse(
    val id: String,
    val imageUrl: String,
    val title :String
)