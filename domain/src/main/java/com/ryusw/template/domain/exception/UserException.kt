package com.ryusw.template.domain.exception

sealed class UserException : Exception{
    constructor() : super()
    constructor(msg : String) : super(msg)
    constructor(msg : String, throwable : Throwable) : super(msg, throwable)
    class UserNotFoundException(msg : String) : UserException(msg)
}