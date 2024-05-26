package com.ryusw.template.data.remote.api

import com.ryusw.template.data.remote.dto.user.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface UserApi {
    @POST("/join")
    suspend fun join(
        @Body accessToken : String
    ) : UserResponseDto
}