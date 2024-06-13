package com.ryusw.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryusw.domain.usecase.auth.CheckApiKeyValidateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkApiKeyValidateUseCase: CheckApiKeyValidateUseCase
) : ViewModel() {
    init {
        validateApiKey()
    }

    private val _action: MutableSharedFlow<SplashAction> = MutableSharedFlow()
    val action: SharedFlow<SplashAction> get() = _action.asSharedFlow()

    private fun validateApiKey() {
        viewModelScope.launch {
            kotlin.runCatching {
                checkApiKeyValidateUseCase()
            }.onSuccess {
                _action.emit(SplashAction.NavigateToLogin)
            }.onFailure {
                _action.emit(SplashAction.ShowToast("알 수 없는 오류가 발생했습니다."))
            }
        }
    }
}

sealed interface SplashAction {
    data object NavigateToLogin : SplashAction
    class ShowToast(val message: String) : SplashAction
}