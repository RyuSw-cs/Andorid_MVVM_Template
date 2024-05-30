package com.ryusw.feature.splash

sealed interface SplashAction {
    data object NavigateToLogin : SplashAction
    class ShowToast(val message: String) : SplashAction
}