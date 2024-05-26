package com.ryusw.template.data.remote.api

import com.ryusw.template.data.remote.dto.auth.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {
    @POST("/login")
    suspend fun login(
        @Body accessToken : String
    ) : AuthResponseDto
}