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
import com.lukic.movieapp.databinding.ItemSearchBinding
import com.lukic.movieapp.ui.SearchUIState

class SearchAdapter(
    private val onCardClick: (Int) -> Unit
) : ListAdapter<SearchUIState, SearchAdapter.SearchViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(ItemSearchBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: SearchUIState) {
            with(binding) {
                searchImage.apply {
                    if (movie.posterPath.removePrefix(BuildConfig.DOMAIN_BASE_IMAGE).isEmpty()) {
                        setImageResource(R.drawable.tmdb_logo)
                    } else {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        load(movie.posterPath)
                    }
                }
                searchMovieTitle.text = movie.title
                searchMovieDescription.text = movie.description
                searchMovieCard.setOnClickListener {
                    onCardClick(movie.movieId)
                }
            }
        }
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<SearchUIState>() {

    override fun areItemsTheSame(oldItem: SearchUIState, newItem: SearchUIState): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(oldItem: SearchUIState, newItem: SearchUIState): Boolean =
        oldItem == newItem
}
