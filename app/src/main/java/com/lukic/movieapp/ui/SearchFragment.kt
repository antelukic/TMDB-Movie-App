package com.lukic.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.MOVIE_ID_KEY
import com.lukic.movieapp.databinding.FragmentSearchBinding
import com.lukic.movieapp.ui.adapters.SearchAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchAdapter = SearchAdapter { movieId ->
            findNavController().previousBackStackEntry?.savedStateHandle?.set(MOVIE_ID_KEY, movieId)
        }

        with(binding) {
            searchEditText.doOnTextChanged { text, _, _, _ ->
                viewModel.refreshSearchMovies(text.toString())
            }
            searchMovies.adapter = searchAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { movies ->
                    searchAdapter.submitList(movies)
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
