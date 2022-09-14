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
import com.lukic.movieapp.databinding.ItemFavouritesBinding
import com.lukic.movieapp.ui.FavouritesUIState

class FavouritesAdapter(
    private val onImageClick: (Int) -> Unit,
    private val onFavouritesSelectorClicked: (FavouritesUIState) -> Unit
) : ListAdapter<FavouritesUIState, FavouritesAdapter.FavouritesViewHolder>(
    FavouritesDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder =
        FavouritesViewHolder(
            binding = ItemFavouritesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class FavouritesViewHolder(private val binding: ItemFavouritesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: FavouritesUIState) {
            with(binding) {
                favouritesImage.apply {
                    if (movie.posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE).isEmpty()) {
                        setImageResource(R.drawable.tmdb_logo)
                    } else {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        load(movie.posterPath)
                    }
                    setOnClickListener {
                        onImageClick(movie.movieID)
                    }
                }
                favouritesFavouritesSelector.apply {
                    setImageResource(R.drawable.ic_filled_heart_with_background)
                    setOnClickListener {
                        onFavouritesSelectorClicked(movie)
                    }
                }
            }
        }
    }
}

private class FavouritesDiffUtilCallback : DiffUtil.ItemCallback<FavouritesUIState>() {

    override fun areItemsTheSame(oldItem: FavouritesUIState, newItem: FavouritesUIState): Boolean =
        newItem.movieID == oldItem.movieID

    override fun areContentsTheSame(
        oldItem: FavouritesUIState,
        newItem: FavouritesUIState
    ): Boolean =
        oldItem == newItem
}
