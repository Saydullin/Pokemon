package com.saydullin.pokemon.app.utils

import android.content.Context
import com.saydullin.pokemon.R
import com.saydullin.pokemon.domain.utils.StatusCode

class StatusText(
    private val context: Context,
    private val statusCode: StatusCode
) {

    fun getCaption(): String {

        return when(statusCode) {
            StatusCode.SERVER_ERROR -> {
                context.getString(R.string.server_error)
            }
            StatusCode.CONNECTION_ERROR -> {
                context.getString(R.string.connection_error)
            }
            StatusCode.DATA_ERROR -> {
                context.getString(R.string.data_error)
            }
            StatusCode.DATA_NOT_FOUND -> {
                context.getString(R.string.data_not_found_error)
            }
            StatusCode.SUCCESS -> {
                context.getString(R.string.success)
            }
            else -> {
                context.getString(R.string.unknown_error)
            }
        }
    }

}