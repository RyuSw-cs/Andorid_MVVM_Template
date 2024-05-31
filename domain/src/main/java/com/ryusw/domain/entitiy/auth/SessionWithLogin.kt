package com.ryusw.domain.entitiy.auth

data class SessionWithLogin (
    val expiresAt : String,
    val requestToken : String
)