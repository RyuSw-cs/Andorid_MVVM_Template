package com.ryusw.data.remote.mapper

import com.ryusw.data.remote.dto.auth.ResponseLoginDto
import com.ryusw.data.remote.dto.auth.ResponseRequestTokenDto
import com.ryusw.domain.entitiy.auth.RequestToken
import com.ryusw.domain.entitiy.auth.SessionWithLogin

internal fun ResponseRequestTokenDto.toDomain() = RequestToken(
    expiresAt = expiresAt,
    requestToken = requestToken
)

internal fun ResponseLoginDto.toDomain() = SessionWithLogin(
    expiresAt = expiresAt,
    requestToken = requestToken
)