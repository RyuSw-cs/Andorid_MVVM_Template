package com.ryusw.domain.entity.auth

data class Session (
    val expiresAt : String,
    val requestToken : String
)