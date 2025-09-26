package com.andressalvatierra.programovil.features.movie.domain.usecase

import com.andressalvatierra.programovil.features.movie.domain.model.MovieModel
import com.andressalvatierra.programovil.features.movie.domain.repository.IMoviesRepository

class FetchPopularMoviesUseCase(
    private val movieRepository: IMoviesRepository
) {
    suspend fun invoke(): Result<List<MovieModel>> {
        return movieRepository.fetchPopularMovies()
    }
}