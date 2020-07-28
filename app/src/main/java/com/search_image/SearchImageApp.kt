package com.search_image

import com.search_image.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SearchImageApp : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<SearchImageApp> {
        return DaggerApplicationComponent.factory()
            .create(this)
    }
}