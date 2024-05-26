package com.ryusw.template.data.remote.dto.auth

import com.ryusw.template.domain.entitiy.auth.Token

internal data class AuthResponseDto (
    val id : String,
    val token : String
)