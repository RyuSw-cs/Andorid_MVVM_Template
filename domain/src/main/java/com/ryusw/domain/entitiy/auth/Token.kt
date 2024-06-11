package com.ryusw.domain.entitiy.auth

data class Token (
    val expiresAt : String,
    val requestToken : String
)