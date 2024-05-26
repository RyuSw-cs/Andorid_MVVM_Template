package com.ryusw.template.domain.exception.auth

sealed class AuthException : Exception{
    constructor() : super()
    constructor(msg : String) : super(msg)
    constructor(msg : String, throwable : Throwable) : super(msg, throwable)
    class TokenExpiredException(msg : String) : AuthException(msg)
    class EmptyTokenException(msg : String) : AuthException(msg)
    class InvalidTokenException(msg : String) : AuthException(msg)
}