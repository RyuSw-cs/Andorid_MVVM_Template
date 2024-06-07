package com.ryusw.feature.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ryusw.feature.movie.list.model.MovieListUiModel
import ryusw.feature.movie.list.R
import ryusw.feature.movie.list.databinding.ItemMovieListBinding

class MovieListAdapter (
    private val vm : MovieListViewModel
) : ListAdapter<MovieListUiModel, MovieListAdapter.MovieListViewHolder>(MovieListDiffUtil){

    inner class MovieListViewHolder(private val binding : ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : MovieListUiModel){
            binding.vm = vm
            binding.data = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_list, parent, false)
    )

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(currentList[position])
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