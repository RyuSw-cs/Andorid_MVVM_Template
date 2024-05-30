package com.ryusw.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryusw.domain.usecase.auth.CheckApiKeyValidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkApiKeyValidate: CheckApiKeyValidate
) : ViewModel() {
    init {
        validateApiKey(BuildConfig.API_KEY)
    }

    private val _action: MutableSharedFlow<SplashAction> = MutableSharedFlow()
    val action: SharedFlow<SplashAction> get() = _action.asSharedFlow()

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> get() = _state.asStateFlow()

    private fun validateApiKey(apiKey: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                checkApiKeyValidate(apiKey)
            }.onSuccess { validateResult ->
                _state.update { state ->
                    state.copy(
                        validateSuccess = validateResult
                    )
                }
            }.onFailure {
                _action.emit(SplashAction.ShowToast(it.message.toString()))
            }
        }
    }
}