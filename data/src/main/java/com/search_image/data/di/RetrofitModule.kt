package com.search_image.data.di

import com.search_image.data.BASE_URL
import com.search_image.data.InternetConnectivity
import com.search_image.data.InternetConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
abstract class InternetConnectivityModule {
    @Binds
    abstract fun bindInternetConnectivity(internetConnectivityImpl: InternetConnectivityImpl): InternetConnectivity
}

@Module
object LoggingInterceptorModule {
    @IntoSet
    @Provides
    @JvmStatic
    fun loggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}

@Module(includes = [InternetConnectivityModule::class, LoggingInterceptorModule::class])
class RetrofitModule {
    private val CONNECT_TIMEOUT = 60L
    private val READ_TIMEOUT = 60L
    private val WRITE_TIMEOUT = 60L

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)// set base url for the client
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).apply {
                interceptors.forEach { addInterceptor(it) }
            }
            .build()
    }
}
