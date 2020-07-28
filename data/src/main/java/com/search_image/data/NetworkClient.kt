package com.search_image.data

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class NetworkClient @Inject constructor(private val retrofit: Retrofit) {
    fun <T : Any> create(clazz: KClass<T>): T {
        return retrofit.create(clazz.java)
    }
}