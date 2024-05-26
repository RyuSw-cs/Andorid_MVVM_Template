package com.ryusw.template.data.util

internal object HandleApi {
    internal inline fun <T> callApi(
        mapper: () -> T
    ): T? {
        return try {
            mapper.invoke()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}