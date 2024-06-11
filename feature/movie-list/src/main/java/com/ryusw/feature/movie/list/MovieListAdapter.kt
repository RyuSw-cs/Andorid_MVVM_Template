package com.ryusw.feature.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.feature.movie.list.databinding.ItemMovieListBinding
import com.ryusw.feature.movie.list.model.MovieListUiModel

class MovieListAdapter (
    private val viewModel : MovieListViewModel
) : ListAdapter<MovieListUiModel, MovieListAdapter.MovieListViewHolder>(MovieListDiffUtil){

    inner class MovieListViewHolder(val binding : ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_list, parent, false)
    )

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        with(holder.binding){
            data = currentList[position]
            vm = viewModel
        }
    }

    object MovieListDiffUtil : DiffUtil.ItemCallback<MovieListUiModel>(){
        override fun areItemsTheSame(
            oldItem: MovieListUiModel,
            newItem: MovieListUiModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListUiModel,
            newItem: MovieListUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}