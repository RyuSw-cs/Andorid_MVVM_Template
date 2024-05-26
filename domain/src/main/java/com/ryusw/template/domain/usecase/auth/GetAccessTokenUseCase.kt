package com.ryusw.template.domain.usecase.auth

import com.ryusw.template.domain.repository.AuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() : Result<String> {
        return authRepository.getAccessToken()
    }
}