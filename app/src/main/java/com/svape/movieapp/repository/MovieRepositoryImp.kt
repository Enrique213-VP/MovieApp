package com.svape.movieapp.repository

import com.svape.movieapp.data.local.LocalMovieDataSource
import com.svape.movieapp.data.model.MovieList
import com.svape.movieapp.data.model.toMovieEntity
import com.svape.movieapp.data.remote.MovieDataSource

class MovieRepositoryImp(
    private val dataSourceRemote: MovieDataSource.RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpcomingMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("upcoming"))
        }
        return dataSourceLocal.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("toprated"))
        }
        return dataSourceLocal.getTopRatedMovies()

    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach {
            dataSourceLocal.saveMovie(it.toMovieEntity("popular"))
        }
        return dataSourceLocal.getPopularMovies()

    }
}