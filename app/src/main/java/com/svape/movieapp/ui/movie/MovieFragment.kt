package com.svape.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.svape.movieapp.R
import com.svape.movieapp.core.Resource
import com.svape.movieapp.data.remote.MovieDataSource
import com.svape.movieapp.databinding.FragmentMovieBinding
import com.svape.movieapp.presentation.MovieViewModel
import com.svape.movieapp.presentation.MovieViewModelFactory
import com.svape.movieapp.repository.MovieRepositoryImp
import com.svape.movieapp.repository.RetrofitClient


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding

    //Inject dependency
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImp(
                MovieDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)


        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading ->{
                    Log.d("SiSe", "Loading...")
                }
                is Resource.Success ->{
                    Log.d("SiSe", " ${result.data.first}")
                }
                is Resource.Failure ->{
                    Log.d("SiSe", " ${result.exception}")
                }
            }

        })
    }

}