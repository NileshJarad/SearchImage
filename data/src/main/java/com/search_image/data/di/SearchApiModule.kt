package com.search_image.data.di

import com.search_image.data.NetworkClient
import com.search_image.data.SearchImageService
import dagger.Module
import dagger.Provides


@Module
abstract class SearchApiModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun resourceService(client: NetworkClient) = client.create(SearchImageService::class)
    }
}