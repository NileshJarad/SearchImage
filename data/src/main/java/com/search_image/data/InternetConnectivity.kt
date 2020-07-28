package com.search_image.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

@Suppress("DEPRECATION")
class InternetConnectivityImpl @Inject constructor(context: Application) : InternetConnectivity {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    override fun isOnline(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}

interface InternetConnectivity {
    fun isOnline(): Boolean
}