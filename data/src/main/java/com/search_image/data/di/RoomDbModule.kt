package com.search_image.data.di

import android.app.Application
import androidx.room.Room
import com.search_image.data.storage.ImageDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDbModule {
    @Singleton
    @Provides
    fun imageDbInstance(context: Application): ImageDb {
        return Room.databaseBuilder(context, ImageDb::class.java, "image_db").build()
    }
}