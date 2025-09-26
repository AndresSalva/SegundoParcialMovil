package com.andressalvatierra.programovil.features.movie.data.datasource

import com.andressalvatierra.programovil.features.movie.data.database.dao.IMovieDao
import com.andressalvatierra.programovil.features.movie.data.database.entity.MovieEntity

class MovieLocalDataSource(private val movieDao: IMovieDao) {
    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
}