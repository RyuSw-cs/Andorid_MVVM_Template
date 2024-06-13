package com.ryusw.domain.entity.auth

data class Token (
    val expiresAt : String,
    val requestToken : String
)