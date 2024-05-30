package com.ryusw.domain.usecase.auth

import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject

class CheckApiKeyValidate @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(apiKey : String): Boolean {
        return authRepository.checkApiKeyValidate(apiKey)
    }
}