package com.search_image.di

import android.app.Application
import androidx.annotation.Keep
import com.search_image.SearchImageApp
import com.search_image.data.di.RepositoryModule
import com.search_image.data.di.RetrofitModule
import com.search_image.data.di.RoomDbModule
import com.search_image.data.di.SearchApiModule
import com.search_image.domain.search.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        RoomDbModule::class,
        RetrofitModule::class,
        UseCaseModule::class,
        SearchApiModule::class,
        ActivitiesModule::class,
        FragmentsModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<SearchImageApp> {
    @Keep
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }
}