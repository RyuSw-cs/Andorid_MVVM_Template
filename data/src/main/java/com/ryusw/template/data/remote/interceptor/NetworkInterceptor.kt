package com.ryusw.template.data.remote.interceptor

import com.ryusw.template.data.local.datasource.AuthDataStore
import com.ryusw.template.data.remote.api.AuthApi
import com.ryusw.template.domain.exception.AuthException
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * 토큰 만료 시, 갱신을 위한 인터셉터
 * */
internal class NetworkInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val authApi: AuthApi
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        var accessToken = ""

        if(response.code == 401){
            runBlocking {
                val expireAccessToken = authDataStore.getAccessToken()
                if(expireAccessToken.isEmpty()){
                    throw AuthException.EmptyTokenException("토큰이 존재하지 않습니다.")
                }

                accessToken = authApi.refreshToken(expireAccessToken).accessToken
                authDataStore.setAccessToken(accessToken)
            }
        }
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(newRequest)
    }
}