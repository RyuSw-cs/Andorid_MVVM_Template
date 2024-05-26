package com.ryusw.template.data.repository

import com.ryusw.template.data.local.datasource.AuthDataStore
import com.ryusw.template.data.remote.api.AuthApi
import com.ryusw.template.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authDataStore: AuthDataStore
) : AuthRepository {

    override suspend fun getAccessToken(): String {
        kotlin.runCatching { authDataStore.getAccessToken() }
            .onSuccess { return it }
            .onFailure { return "" }
        return ""
    }

    override suspend fun setAccessToken(accessToken: String): Boolean {
        kotlin.runCatching { authDataStore.setAccessToken(accessToken) }
            .onSuccess { return true }
            .onFailure { return false }
        return false
    }

    override suspend fun refreshToken(accessToken: String): Boolean {
        kotlin.runCatching { authApi.refreshToken(accessToken) }
            .onSuccess { return true }
            .onFailure { return false }
        return false
    }

}