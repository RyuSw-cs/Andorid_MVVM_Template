package com.ryusw.template.data.remote.api

import com.ryusw.template.data.remote.dto.auth.RefreshTokenResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {
    @POST("/refresh")
    suspend fun refreshToken(
        @Body accessToken : String
    ) : RefreshTokenResponseDto
}