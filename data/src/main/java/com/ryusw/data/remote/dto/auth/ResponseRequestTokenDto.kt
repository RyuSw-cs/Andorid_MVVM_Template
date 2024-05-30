package com.ryusw.template.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

internal data class ResponseRequestTokenDto(
    @SerializedName("expired_dt")
    val expiredDt : String,
    @SerializedName("request_token")
    val requestToken : String
)