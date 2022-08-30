package com.lukic.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lukic.domain.model.Cast
import com.lukic.movieapp.databinding.ItemCastBinding

class CastAdapter :
    ListAdapter<Cast, CastAdapter.CastViewHolder>(CastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CastViewHolder(
            ItemCastBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            with(binding) {
                castName.text = cast.name
                castRoleName.text = cast.roleName
                castImage.load(cast.posterPath)
            }
        }
    }
}

class CastDiffCallback : DiffUtil.ItemCallback<Cast>() {

    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean =
        areItemsTheSame(oldItem, newItem)
}
