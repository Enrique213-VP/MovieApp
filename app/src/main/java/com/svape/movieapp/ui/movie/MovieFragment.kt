package com.svape.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.svape.movieapp.R
import com.svape.movieapp.core.Resource
import com.svape.movieapp.data.local.AppDatabase
import com.svape.movieapp.data.local.LocalMovieDataSource
import com.svape.movieapp.data.model.Movie
import com.svape.movieapp.data.remote.MovieDataSource
import com.svape.movieapp.databinding.FragmentMovieBinding
import com.svape.movieapp.presentation.MovieViewModel
import com.svape.movieapp.presentation.MovieViewModelFactory
import com.svape.movieapp.repository.MovieRepositoryImp
import com.svape.movieapp.repository.RetrofitClient
import com.svape.movieapp.ui.movie.adapters.MovieAdapter
import com.svape.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.svape.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.svape.movieapp.ui.movie.adapters.concat.UpComingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImp(
                MovieDataSource.RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpComingConcatAdapter(
                                MovieAdapter(
                                    it.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    it.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    it.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )

                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("FetchError", "Error: $it.exception ")
                    Toast.makeText(requireContext(), "Error: ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}