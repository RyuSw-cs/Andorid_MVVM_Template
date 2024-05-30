package com.ryusw.domain.entitiy.auth

data class RequestToken (
    val expiredDt : String,
    val requestToken : String
)