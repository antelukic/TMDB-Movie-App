package com.lukic.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukic.movieapp.adapters.MovieRecyclerAdapter
import com.lukic.movieapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var movieAdapter: MovieRecyclerAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        movieAdapter = MovieRecyclerAdapter()
        val movies = mutableListOf<String>()
        for (i in 0 ..20){
            movies.add(i.toString())
        }
        movieAdapter.submitList(movies)

        with(binding){
            homeFreeRv.adapter = movieAdapter
            homePopularRv.adapter = movieAdapter
            homeTrendingRv.adapter = movieAdapter
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
