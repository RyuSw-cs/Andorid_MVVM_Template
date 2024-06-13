package com.ryusw.data.remote.mapper

import com.ryusw.data.remote.dto.auth.ResponseLoginDto
import com.ryusw.data.remote.dto.auth.ResponseRequestTokenDto
import com.ryusw.domain.entity.auth.Token
import com.ryusw.domain.entity.auth.Session

internal fun ResponseRequestTokenDto.toDomain() = Token(
    expiresAt = expiresAt,
    requestToken = requestToken
)

internal fun ResponseLoginDto.toDomain() = Session(
    expiresAt = expiresAt,
    requestToken = requestToken
)