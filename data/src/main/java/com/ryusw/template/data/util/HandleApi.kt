package com.ryusw.template.data.util

import com.google.gson.Gson
import com.ryusw.template.data.remote.dto.NetworkFailResponse
import com.ryusw.template.domain.exception.AuthException
import retrofit2.HttpException

internal object HandleApi {
    /**
     * API 호출 후 Response에 따라서 처리
     * 성공하면 mapper를 통해 entitiy로 변환
     * 서버에서 실패 Response가 내려오면 Domain의 Exception으로 변환
     * 클라이언트 오류는 Framework에서 사용되는 Exception 사용
     * */
    inline fun <T> callApi(
        mapper: () -> T
    ): T {
        return try {
            mapper.invoke()
        } catch (e: HttpException) {
            throw createServerException(e)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    private fun createServerException(httpException: HttpException) : Throwable {
        val errorResponseBody = httpException.response()?.errorBody()?.string()
        val failResponse = Gson().fromJson(errorResponseBody, NetworkFailResponse::class.java)

        return when (failResponse.statusCode) {
            3 -> AuthException.AuthenticationFailedException(failResponse.statusMessage)
            else -> Exception(failResponse.statusMessage)
        }
    }
}