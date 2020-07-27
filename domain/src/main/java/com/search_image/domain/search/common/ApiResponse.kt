package com.search_image.domain.search.common

sealed class ApiResponse<out Resp> {
    data class Success<out DATA>(val data: DATA) : ApiResponse<DATA>()
    data class Error(val message: String, val errorCode: Int) : ApiResponse<Nothing>()
    object SomethingWenWrong : ApiResponse<Nothing>()
    object NetworkError : ApiResponse<Nothing>()
}