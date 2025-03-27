package com.svape.movieapp.data.remote

import com.svape.movieapp.application.AppConstants
import com.svape.movieapp.data.model.MovieList
import com.svape.movieapp.repository.WebService

class MovieDataSource(private val webService: WebService) {

    class RemoteMovieDataSource(private val webService: WebService) {

        suspend fun getUpcomingMovies(): MovieList {
            return webService.getUpcomingMovies(AppConstants.Api_key)
        }

        suspend fun getTopRatedMovies(): MovieList {
            return webService.getTopRatedMovies(AppConstants.Api_key)
        }

        suspend fun getPopularMovies(): MovieList {
            return webService.getPopularMovies(AppConstants.Api_key)
        }
    }

}