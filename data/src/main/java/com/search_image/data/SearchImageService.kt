package com.search_image.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchImageService {
    @GET("3/gallery/search/{page}")
    suspend fun searchImage(
        @Header("Authorization") authorization: String = authKey,
        @Path("page") page: Int,
        @Query("q_exactly") searchQuery: String,
        @Query("q_size_px") size: String = "small"
    ): Response<GetSearchImageResponse>
}