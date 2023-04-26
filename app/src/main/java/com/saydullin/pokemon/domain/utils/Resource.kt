package com.saydullin.pokemon.domain.utils

enum class StatusCode {
    DATA_ERROR,
    SERVER_ERROR,
    CONNECTION_ERROR,
    UNKNOWN_ERROR,
    SUCCESS,
}

sealed class Resource<T>(
    val data: T? = null,
    val statusCode: StatusCode = StatusCode.UNKNOWN_ERROR,
) {

    class Success<T>(data: T): Resource<T>(data)

    class Error<T>(
        statusCode: StatusCode = StatusCode.UNKNOWN_ERROR,
        data: T? = null
    ): Resource<T>(data, statusCode)

}