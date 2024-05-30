package com.ryusw.template.data.remote.api

import com.ryusw.template.data.remote.dto.NetworkResponse
import com.ryusw.template.data.remote.dto.auth.ResponseRequestTokenDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface AuthApi {
    @GET("authentication/token/new")
    suspend fun requestToken(
        @Query("api_key") apiKey : String
    ) : ResponseRequestTokenDto

    @GET("authentication")
    suspend fun requestApiKeyValidate(
        @Query("api_key") apiKey: String
    ) : NetworkResponse
}