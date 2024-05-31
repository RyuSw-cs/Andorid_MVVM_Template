package com.ryusw.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

internal data class RequestLoginDto(
    @SerializedName("username")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String
)