package com.ryusw.template.data.remote.dto.auth

internal data class RefreshTokenResponseDto (
    val tokenType : String,
    val accessToken : String,
    val refreshToken : String
)