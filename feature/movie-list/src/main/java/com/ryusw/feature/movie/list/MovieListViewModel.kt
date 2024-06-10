package com.ryusw.feature.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryusw.domain.usecase.movie.GetPopularMovieListUseCase
import com.ryusw.feature.movie.list.model.MovieListUiModel
import com.ryusw.feature.movie.list.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ryusw.feature.movie.list.BuildConfig
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase
) : ViewModel() {
    private val _loading: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loading: SharedFlow<Boolean> get() = _loading.asSharedFlow()

    private val _state: MutableStateFlow<MovieListState> = MutableStateFlow(MovieListState())
    val state: StateFlow<MovieListState> get() = _state.asStateFlow()
    private val _action: MutableSharedFlow<MovieListAction> = MutableSharedFlow()
    val action: SharedFlow<MovieListAction> get() = _action.asSharedFlow()

    fun getPopularMovieList() {
        viewModelScope.launch {
            _loading.emit(true)
            runCatching {
                getPopularMovieListUseCase(BuildConfig.TMDB_API_KEY, "ko, en-US", _state.value.page)
            }.onSuccess {data ->
                _loading.emit(false)
                val movieUiModelList = data.map {
                    it.toUiModel()
                }
                _state.update { before ->
                    before.copy(
                        // 기존데이터에 붙이는 형식
                        popularMovieList = before.popularMovieList + movieUiModelList,
                        page = before.page + 1
                    )
                }
            }.onFailure { exception ->
                _loading.emit(false)
                _action.emit(MovieListAction.ShowErrorDialog(exception.message.toString()))
            }
        }
    }

    fun navigateToMovieDetail(id: Int) {
        viewModelScope.launch {
            _action.emit(MovieListAction.NavigateToMovieDetail(id))
        }
    }
}

data class MovieListState(
    val popularMovieList: List<MovieListUiModel> = emptyList(),
    val page : Int = 1
) {
    val isEmpty get() = popularMovieList.isEmpty()
}

sealed interface MovieListAction {
    class NavigateToMovieDetail(val id: Int) : MovieListAction
    class ShowErrorDialog(val title : String = "알림", val content : String = "알 수 없는 오류입니다.") : MovieListAction
}