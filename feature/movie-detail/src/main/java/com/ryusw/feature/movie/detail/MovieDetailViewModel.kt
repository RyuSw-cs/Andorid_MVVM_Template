package com.ryusw.feature.movie.detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryusw.domain.exception.MovieException
import com.ryusw.domain.usecase.movie.GetMovieDetailUseCase
import com.ryusw.feature.movie.detail.model.MovieDetailUiModel
import com.ryusw.feature.movie.detail.model.toUiModel
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
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<MovieDetailState> = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> get() = _state.asStateFlow()

    private val _action: MutableSharedFlow<MovieDetailAction> = MutableSharedFlow()
    val action: SharedFlow<MovieDetailAction> get() = _action.asSharedFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _loading.value = true
            runCatching {
                getMovieDetailUseCase(language = "en-US", movieId = movieId)
            }.onSuccess {
                _loading.value = false
                val movieDetailUiModel = it.toUiModel()
                _state.update { before ->
                    before.copy(
                        movie = movieDetailUiModel
                    )
                }
            }.onFailure {
                _loading.value = false
                when (it) {
                    is MovieException.MovieNotFoundException -> {
                        _action.emit(MovieDetailAction.ShowToastAndBack(it.msg))
                    }
                    else -> {
                        _action.emit(MovieDetailAction.ShowToastAndBack("알 수 없는 오류가 발생했습니다."))
                    }
                }
            }
        }
    }
}

data class MovieDetailState(
    val movie: MovieDetailUiModel = MovieDetailUiModel()
){
    val noData = movie.title.isEmpty()
}

sealed interface MovieDetailAction {
    class ShowToastAndBack(val message: String) : MovieDetailAction
}