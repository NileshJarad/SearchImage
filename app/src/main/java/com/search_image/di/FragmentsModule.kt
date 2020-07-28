package com.search_image.di

import androidx.lifecycle.ViewModel
import com.search_image.detail.ImageDetailFragment
import com.search_image.detail.ImageDetailViewModel
import com.search_image.search.SearchImageFragment
import com.search_image.search.SearchImageViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun provideSearchImageFragment(): SearchImageFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchImageViewModel::class)
    abstract fun bindSearchImageViewModel(searchImageViewModel: SearchImageViewModel): ViewModel


    @ContributesAndroidInjector
    abstract fun provideImageDetailFragment(): ImageDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailViewModel::class)
    abstract fun bindImageDetailViewModel(imageDetailViewModel: ImageDetailViewModel): ViewModel
}