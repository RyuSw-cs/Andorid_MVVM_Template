package com.ryusw.template.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("status_code")
    val statusCode : Int,
    @SerializedName("status_message")
    val statusMessage : String
)