package com.ryusw.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

internal data class ResponseLoginDto(
    @SerializedName("expires_at")
    val expiresAt : String,
    @SerializedName("request_token")
    val requestToken : String
)