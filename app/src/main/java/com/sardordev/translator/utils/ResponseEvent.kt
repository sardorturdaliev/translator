package com.sardordev.translator.utils

sealed class ResponseEvent<T>(data: T?, message: String?) {

    data class Success<T>(val data: T?) : ResponseEvent<T>(data, null)
    data class Error<T>(val message: String?) : ResponseEvent<T>(null, message)

}
