package com.ryusw.data.remote.api

import com.ryusw.data.BuildConfig
import com.ryusw.data.remote.dto.NetworkResponse
import com.ryusw.data.remote.dto.auth.RequestLoginDto
import com.ryusw.data.remote.dto.auth.ResponseLoginDto
import com.ryusw.data.remote.dto.auth.ResponseRequestTokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AuthApi {
    @GET("authentication/token/new")
    suspend fun requestToken(
        @Query("api_key") apiKey : String = BuildConfig.TMDB_API_KEY
    ) : ResponseRequestTokenDto

    @GET("authentication")
    suspend fun requestApiKeyValidate(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : NetworkResponse

    @POST("authentication/token/validate_with_login")
    suspend fun requestLogin(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Body requestLoginDto: RequestLoginDto
    ) : ResponseLoginDto
}