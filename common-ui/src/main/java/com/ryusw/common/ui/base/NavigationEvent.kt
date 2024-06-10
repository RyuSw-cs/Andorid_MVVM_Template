package com.ryusw.common.ui.base

interface NavigationEvent {
    fun navigateSplashToLogin()
    fun navigateLoginToMovieList()
    fun navigateMovieListToMovieInfo(id : Int)
}