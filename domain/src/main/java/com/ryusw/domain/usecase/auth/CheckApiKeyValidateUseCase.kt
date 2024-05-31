package com.ryusw.domain.usecase.auth

import com.ryusw.domain.exception.AuthException
import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject

class CheckApiKeyValidateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(apiKey: String) {
        val validate = authRepository.checkApiKeyValidate(apiKey)
        // TODO 토큰 검증에 실패하면 예외, 성공하면 return을 해주는게 맞을까?
        if (!validate) {
            throw AuthException.InvalidApiKeyException("토큰이 유효하지 않습니다.")
        }
    }
}