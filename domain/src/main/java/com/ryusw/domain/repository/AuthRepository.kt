package com.ryusw.domain.repository

import com.ryusw.domain.entitiy.auth.RequestToken
import com.ryusw.domain.entitiy.auth.SessionWithLogin

interface AuthRepository {
    suspend fun getRequestToken(apiKey: String): RequestToken
    suspend fun checkApiKeyValidate(apiKey: String): Boolean
    suspend fun login(
        id: String,
        password: String,
        requestToken: String,
        apiKey: String
    ): SessionWithLogin
}