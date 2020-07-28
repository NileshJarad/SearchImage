package com.search_image.di

import com.search_image.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
}