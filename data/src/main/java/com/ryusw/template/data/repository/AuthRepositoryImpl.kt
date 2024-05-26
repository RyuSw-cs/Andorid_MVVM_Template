package com.ryusw.template.data.repository

import com.ryusw.template.data.local.datasource.AuthDataStore
import com.ryusw.template.data.remote.api.AuthApi
import com.ryusw.template.data.remote.mapper.toDomain
import com.ryusw.template.data.util.HandleApi
import com.ryusw.template.domain.entitiy.auth.Token
import com.ryusw.template.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDataStore: AuthDataStore
) : AuthRepository {
    override suspend fun login(accessToken: String): Token? {
        return HandleApi.callApi { authApi.login(accessToken).toDomain() }
    }

    override suspend fun saveAccessToken(accessToken: String): Result<Unit> {
        return kotlin.runCatching {
            authDataStore.setAccessToken(accessToken)
        }
    }

    override suspend fun getAccessToken(): Result<String> {
        return kotlin.runCatching {
            authDataStore.getAccessToken()
        }
    }
}