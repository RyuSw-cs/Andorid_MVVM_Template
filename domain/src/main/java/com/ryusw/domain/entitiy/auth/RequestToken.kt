package com.ryusw.domain.entitiy.auth

data class RequestToken (
    val expiresAt : String,
    val requestToken : String
)