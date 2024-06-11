package com.ryusw.data.util

import com.google.gson.Gson
import com.ryusw.data.remote.dto.NetworkResponse
import com.ryusw.domain.entitiy.movie.Movie
import com.ryusw.domain.exception.AuthException
import com.ryusw.domain.exception.MovieException
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

    private fun createServerException(httpException: HttpException): Throwable {
        val errorResponseBody = httpException.response()?.errorBody()?.string()
        val failResponse = Gson().fromJson(errorResponseBody, NetworkResponse::class.java)

        return when (failResponse.statusCode) {
            3 -> AuthException.AuthenticationFailedException(failResponse.statusMessage)
            33 -> AuthException.InvalidRequestTokenException(failResponse.statusMessage)
            30 -> AuthException.InvalidAccountException(failResponse.statusMessage)
            34 -> MovieException.MovieNotFoundException(failResponse.statusMessage)
            else -> Exception(failResponse.statusMessage)
        }
    }
}