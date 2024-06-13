package com.ryusw.feature.movie.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ryusw.common.ui.base.BaseFragment
import com.ryusw.common.ui.base.NavigationEvent
import com.ryusw.feature.movie.detail.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_movie_detail
    override val viewModel: MovieDetailViewModel by viewModels()

    override fun initView() {
        viewModel.getMovieDetail(arguments?.getInt("id") ?: -1)
    }

    override fun initDataBinding() {
        binding.vm = viewModel
    }

    override fun initObserving() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.state.collect { state ->
                        if (state.noData) {
                            binding.layoutMain.visibility = View.INVISIBLE
                        } else {
                            binding.layoutMain.visibility = View.VISIBLE
                            binding.data = state.movie
                        }
                    }
                }

                launch {
                    viewModel.loading.collect {loadingState ->
                        if(loadingState) {
                            showLoadingDialog()
                        } else {
                            dismissLoadingDialog()
                        }
                    }
                }

                launch {
                    viewModel.action.collect { action ->
                        when (action) {
                            is MovieDetailAction.ShowToastAndBack -> {
                                showToast(action.message)
                                (activity as NavigationEvent).navigateMovieDetailToMovieList()
                            }
                        }
                    }
                }
            }
        }
    }
}