package com.andressalvatierra.programovil.features.movie.domain.repository

import com.andressalvatierra.programovil.features.movie.domain.model.MovieModel

interface IMoviesRepository {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>>
}