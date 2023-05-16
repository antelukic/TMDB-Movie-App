package com.lukic.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lukic.movieapp.BuildConfig
import com.lukic.movieapp.R
import com.lukic.movieapp.databinding.ItemMovieBinding
import com.lukic.movieapp.ui.HomeMovieUIState

class MovieAdapter(
    private val onCardClick: (Int) -> Unit,
    private val onFavouriteClick: (HomeMovieUIState) -> Unit
) : ListAdapter<HomeMovieUIState, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(ItemMovieBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(this.getItem(position))

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: HomeMovieUIState) {
            with(binding) {
                with(movieImage) {
                    setOnClickListener {
                        onCardClick(movie.movieID)
                    }
                    if (movie.posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE).isEmpty()) {
                        setImageResource(R.drawable.tmdb_logo)
                    } else {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        load(movie.posterPath)
                    }
                }
                with(movieFavouritesSelector) {
                    setImageResource(
                        if (movie.isFavourite) {
                            R.drawable.ic_filled_heart_with_background
                        } else {
                            R.drawable.ic_heart
                        }
                    )
                    setOnClickListener {
                        onFavouriteClick(movie)
                    }
                }
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<HomeMovieUIState>() {

    override fun areItemsTheSame(oldItem: HomeMovieUIState, newItem: HomeMovieUIState): Boolean =
        oldItem.movieID == newItem.movieID

    override fun areContentsTheSame(oldItem: HomeMovieUIState, newItem: HomeMovieUIState): Boolean =
        oldItem == newItem
}
