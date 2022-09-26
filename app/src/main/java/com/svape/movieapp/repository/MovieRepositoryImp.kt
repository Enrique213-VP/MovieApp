package com.svape.movieapp.repository

import com.svape.movieapp.data.model.MovieList
import com.svape.movieapp.data.remote.MovieDataSource

class MovieRepositoryImp(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getUpComingMovies(): MovieList = dataSource.getUpComingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()


}