package com.ryusw.domain.repository

import com.ryusw.domain.entitiy.auth.Token
import com.ryusw.domain.entitiy.auth.Session

interface AuthRepository {
    suspend fun getRequestToken(): Token
    suspend fun checkApiKeyValidate(): Boolean
    suspend fun login(
        id: String,
        password: String,
        requestToken: String,
    ): Session
}