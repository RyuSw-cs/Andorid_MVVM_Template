package com.ryusw.data.remote.interceptor

import com.ryusw.data.local.datasource.AuthDataStore
import com.ryusw.data.remote.api.AuthApi
import com.ryusw.domain.exception.AuthException
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * 토큰 만료 시, 갱신을 위한 인터셉터
 * */
internal class NetworkInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        var accessToken = ""

        if(response.code == 401){
            runBlocking {
                val expireAccessToken = authDataStore.getAccessToken()
                if(expireAccessToken.isEmpty()){
                    // TODO 토큰을 사용한다면 주석 해제
//                    throw AuthException.EmptyTokenException("토큰이 존재하지 않습니다.")
                }

                // TODO AuthApi에 Token Refresh API 호출
                authDataStore.setAccessToken(accessToken)
            }
        }
        // TODO 새로 발급받은 토큰을 헤더에 적용
        val newRequest = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(newRequest)
    }
}