package com.ryusw.domain.usecase.auth

import com.ryusw.domain.repository.AuthRepository
import javax.inject.Inject
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(id : String, password : String, requestToken : String) : Boolean {
        val loginResult = authRepository.login(id, password, requestToken)
        return loginResult.requestToken.isNotEmpty()
    }
}