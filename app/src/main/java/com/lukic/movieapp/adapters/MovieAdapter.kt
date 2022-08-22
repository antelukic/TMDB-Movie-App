package com.lukic.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lukic.movieapp.Movie
import com.lukic.movieapp.databinding.ItemMovieBinding

class MovieRecyclerAdapter(
    private val onClick: (String, View) -> Unit
) : ListAdapter<Movie, MovieRecyclerAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(this.getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                movieImage.setOnClickListener {
                    onClick(movie.title, it)
                }
                ViewCompat.setTransitionName(movieImage, movie.title)
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        areItemsTheSame(oldItem, newItem)
}
