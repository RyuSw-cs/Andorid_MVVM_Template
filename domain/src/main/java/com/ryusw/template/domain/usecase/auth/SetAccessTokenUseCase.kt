package com.ryusw.template.domain.usecase.auth

import com.ryusw.template.domain.repository.AuthRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(accessToken : String) : Result<Unit>{
        return authRepository.setAccessToken(accessToken)
    }
}