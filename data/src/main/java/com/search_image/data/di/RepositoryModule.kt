package com.search_image.data.di

import com.search_image.data.ImageDetailsRepositoryImpl
import com.search_image.data.SearchImageRepositoryImpl
import com.search_image.domain.search.ImageDetailsRepository
import com.search_image.domain.search.SearchImageRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun searchRepository(searchRepositoryImpl: SearchImageRepositoryImpl): SearchImageRepository

    @Binds
    abstract fun imageDetailsRepository(imageDetailsRepositoryImpl: ImageDetailsRepositoryImpl): ImageDetailsRepository
}