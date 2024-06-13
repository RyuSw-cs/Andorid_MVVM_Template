package com.ryusw.data.util

import com.google.gson.Gson
import com.ryusw.data.remote.dto.NetworkResponse
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
            e.printStackTrace()
            throw Exception(e)
        }
    }

    private fun createServerException(httpException: HttpException): Throwable {
        val errorResponseBody = httpException.response()?.errorBody()?.string()
        val failResponse = Gson().fromJson(errorResponseBody, NetworkResponse::class.java)

        return when (failResponse.statusCode) {
            3 -> AuthException.AuthenticationFailedException("서비스에 접근할 수 없습니다")
            30 -> AuthException.InvalidAccountException("ID/PASSWORD가 일치하지 않습니다")
            32 -> AuthException.AuthenticationFailedException("유효하지 않는 요청 토큰입니다")
            33 -> AuthException.InvalidRequestTokenException("만료된 요청 토큰입니다")
            34 -> MovieException.MovieNotFoundException("올바른 입력이 아닙니다")
            else -> Exception(failResponse.statusMessage)
        }
    }
}