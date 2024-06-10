package com.ryusw.data.remote.interceptor

import com.ryusw.data.local.datasource.AuthDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class TokenInterceptor @Inject constructor(
    private val authDataStore: AuthDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()

        // TODO runBlocking이 최선일까?
//        runBlocking {
//            newRequest.addHeader("Authorization", "Bearer ${authDataStore.getAccessToken()}")
//        }
        return chain.proceed(newRequest.build())
    }
}