package com.ryusw.domain.entitiy.auth

data class Session (
    val expiresAt : String,
    val requestToken : String
)