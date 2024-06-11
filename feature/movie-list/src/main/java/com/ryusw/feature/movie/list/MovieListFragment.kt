package com.ryusw.feature.movie.list

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.common.ui.base.BaseFragment
import com.ryusw.common.ui.dialog.CommonDialogFragment
import com.ryusw.common.ui.logger.RyuSwLogger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ryusw.feature.movie.list.R
import ryusw.feature.movie.list.databinding.FragmentMovieListBinding

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_movie_list
    override val viewModel: MovieListViewModel by viewModels()

    private val movieAdapter by lazy { MovieListAdapter(viewModel) }

    override fun initView() {
        viewModel.getPopularMovieList()
        with(binding) {
            listMovie.adapter = movieAdapter

            listMovie.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // TODO Paging 적용해야함
                    if(!recyclerView.canScrollVertically(1)){
                        viewModel.getPopularMovieList()
                    }
                }
            })
        }
    }

    override fun initDataBinding() {
        with(binding) {
            vm = viewModel
        }
    }

    override fun initObserving() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { state ->
                        binding.viewListState.visibility =
                            if (state.isEmpty) View.VISIBLE else View.GONE
                        movieAdapter.submitList(state.popularMovieList)
                    }
                }

                launch {
                    viewModel.loading.collect { loadingState ->
                        if (loadingState) {
                            showLoadingDialog()
                        } else {
                            dismissLoadingDialog()
                        }
                    }
                }

                launch {
                    viewModel.action.collect { action ->
                        when (action) {
                            is MovieListAction.NavigateToMovieDetail -> {

                            }

                            is MovieListAction.ShowErrorDialog -> {
                                CommonDialogFragment(
                                    title = action.title,
                                    content = action.content
                                ).show(childFragmentManager, null)
                            }
                        }
                    }
                }
            }
        }
    }
}