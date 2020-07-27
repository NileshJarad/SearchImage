package com.search_image.domain.search.di

import com.search_image.domain.search.SearchImageUseCase
import com.search_image.domain.search.SearchImageUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun searchRepository(searchRepositoryImpl: SearchImageUseCaseImpl): SearchImageUseCase
}