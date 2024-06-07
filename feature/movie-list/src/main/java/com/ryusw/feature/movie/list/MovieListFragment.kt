package com.ryusw.feature.movie.list

import androidx.fragment.app.viewModels
import com.ryusw.common.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ryusw.feature.movie.list.R
import ryusw.feature.movie.list.databinding.FragmentMovieListBinding

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_movie_list
    override val viewModel: MovieListViewModel by viewModels()

    private val movieAdapter by lazy { MovieListAdapter(viewModel) }

    override fun initView() {
        with(binding){
            listMovie.adapter = movieAdapter
        }
    }

    override fun initDataBinding() {
        with(binding){
            vm = viewModel
        }
    }

    override fun initObserving() {

    }
}