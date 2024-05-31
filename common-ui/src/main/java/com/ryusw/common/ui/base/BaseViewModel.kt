package com.ryusw.common.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
open class BaseViewModel : ViewModel() {
    protected val _loading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading : StateFlow<Boolean> = _loading.asStateFlow()
}