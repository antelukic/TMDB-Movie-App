package com.lukic.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lukic.movieapp.databinding.ItemMovieBinding
import com.lukic.movieapp.ui.HomeMovieUIState

class MovieAdapter(
    private val onClick: (Int, View) -> Unit
) : ListAdapter<HomeMovieUIState, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(this.getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: HomeMovieUIState) {
            with(binding) {
                with(movieImage) {
                    setOnClickListener { view ->
                        onClick(movie.movieID, view)
                    }
                    ViewCompat.setTransitionName(this, movie.movieID.toString())
                    load(movie.posterPath)
                }
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<HomeMovieUIState>() {

    override fun areItemsTheSame(oldItem: HomeMovieUIState, newItem: HomeMovieUIState): Boolean =
        oldItem.movieID == newItem.movieID

    override fun areContentsTheSame(oldItem: HomeMovieUIState, newItem: HomeMovieUIState): Boolean =
        areItemsTheSame(oldItem, newItem)
}
