package com.svape.movieapp.data.remote

import com.svape.movieapp.data.model.MovieList

class MovieDataSource {

    fun getUpComingMovies(): MovieList{
        return MovieList()
    }
    fun getTopRatedMovies(): MovieList{
        return MovieList()
    }
    fun getPopularMovies(): MovieList{
        return MovieList()
    }



}