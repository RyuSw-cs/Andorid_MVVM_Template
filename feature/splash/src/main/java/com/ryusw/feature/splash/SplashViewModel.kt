package com.ryusw.feature.splash

import androidx.lifecycle.viewModelScope
import com.ryusw.common.ui.base.BaseViewModel
import com.ryusw.common.ui.logger.RyuSwLogger
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
) : BaseViewModel() {
    init {
        validateApiKey()
    }

    private val _action: MutableSharedFlow<SplashAction> = MutableSharedFlow()
    val action: SharedFlow<SplashAction> get() = _action.asSharedFlow()

    private fun validateApiKey() {
        viewModelScope.launch {
            RyuSwLogger.v(CLASSNAME, "validateApiKey", "start")
            _loading.emit(true)
            kotlin.runCatching {
                checkApiKeyValidateUseCase(BuildConfig.TMDB_API_KEY)
            }.onSuccess {
                RyuSwLogger.v(CLASSNAME, "validateApiKey", "success")
                _loading.emit(false)
                _action.emit(SplashAction.NavigateToLogin)
            }.onFailure {
                RyuSwLogger.w(CLASSNAME, "validateApiKey", "exception = ${it.message}")
                _loading.emit(false)
                _action.emit(SplashAction.ShowToast(it.message.toString()))
            }
            RyuSwLogger.v(CLASSNAME, "validateApiKey", "end")
        }
    }

    companion object {
        private const val CLASSNAME = "SplashViewModel"
    }
}