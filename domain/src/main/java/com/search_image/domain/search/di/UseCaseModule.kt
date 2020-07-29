package com.search_image.domain.search.di

import com.search_image.domain.search.GetCommentUseCase
import com.search_image.domain.search.GetCommentUseCaseImpl
import com.search_image.domain.search.PostCommentUseCase
import com.search_image.domain.search.PostCommentUseCaseImpl
import com.search_image.domain.search.SearchImageUseCase
import com.search_image.domain.search.SearchImageUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun searchRepository(searchRepositoryImpl: SearchImageUseCaseImpl): SearchImageUseCase

    @Binds
    abstract fun postCommentUseCase(postCommentUseCaseImpl: PostCommentUseCaseImpl): PostCommentUseCase

    @Binds
    abstract fun getCommentUseCase(getCommentUseCaseImpl: GetCommentUseCaseImpl): GetCommentUseCase
}