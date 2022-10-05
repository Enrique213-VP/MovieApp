package com.svape.movieapp.data.remote

import com.svape.movieapp.application.AppConstants
import com.svape.movieapp.data.model.MovieList
import com.svape.movieapp.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpComingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.Api_key)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.Api_key)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.Api_key)



}