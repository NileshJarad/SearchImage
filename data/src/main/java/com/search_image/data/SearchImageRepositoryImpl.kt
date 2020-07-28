package com.search_image.data

import com.search_image.domain.search.SearchImageRepository
import com.search_image.domain.search.SearchImageResponse
import com.search_image.domain.search.common.ApiResponse
import javax.inject.Inject

class SearchImageRepositoryImpl @Inject constructor(
    private val searchImageService: SearchImageService,
    private val internetConnectivity: InternetConnectivity
) : SearchImageRepository {

    private var images = mutableListOf<SearchImageResponse>()

    override suspend fun searchImage(
        searchQuery: String,
        pageCount: Int
    ): ApiResponse<List<SearchImageResponse>> {
        return if (internetConnectivity.isOnline()) {
            //this is fresh call so clear all previously store search image data
            if (pageCount == 1) {
                images.clear()
            }

            val response = searchImageService.searchImage(
                page = pageCount,
                searchQuery = searchQuery
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    parseSearchResult(it)
                }
                ApiResponse.Success(images)
            } else {
                ApiResponse.SomethingWenWrong
            }
        } else {
            ApiResponse.NetworkError
        }
    }

    private fun parseSearchResult(it: GetSearchImageResponse) {
        it.data?.let { dataList ->
            dataList.forEach { data ->
                data?.images?.let { imageList ->
                    imageList.forEach {
                        if (it?.type?.startsWith("image/") == true)
                            images.add(
                                SearchImageResponse(
                                    id = it.id ?: "",
                                    imageUrl = it.link ?: "",
                                    title = it.title ?: ""
                                )
                            )
                    }
                }
            }
        }
    }
}