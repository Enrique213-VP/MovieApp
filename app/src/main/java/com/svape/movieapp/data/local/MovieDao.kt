package com.svape.movieapp.data.local

import androidx.room.*
import com.svape.movieapp.data.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}