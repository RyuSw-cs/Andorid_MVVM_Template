package com.ryusw.template.domain.repository

import com.ryusw.template.domain.entitiy.auth.RequestToken

interface AuthRepository {
    suspend fun getRequestToken() : RequestToken
}