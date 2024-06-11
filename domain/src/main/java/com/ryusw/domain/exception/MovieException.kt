package com.ryusw.domain.exception

sealed class MovieException : Exception {
    constructor() : super()
    constructor(msg: String) : super(msg)
    constructor(msg: String, throwable: Throwable) : super(msg, throwable)
    class MovieNotFoundException(val msg : String) : MovieException()
    class MovieInvalidPageException(msg : String) : MovieException()
}