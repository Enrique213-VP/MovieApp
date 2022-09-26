package com.svape.movieapp.repository

import com.svape.movieapp.data.model.MovieList

interface MovieRepository {

    suspend fun getUpComingMovies() : MovieList
    suspend fun getTopRatedMovies() : MovieList
    suspend fun getPopularMovies() : MovieList
}