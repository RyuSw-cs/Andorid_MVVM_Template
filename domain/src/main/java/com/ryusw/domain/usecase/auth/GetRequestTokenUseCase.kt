package com.ryusw.domain.usecase.auth

import com.ryusw.domain.entitiy.auth.Token
import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() : Token {
        return authRepository.getRequestToken()
    }
}