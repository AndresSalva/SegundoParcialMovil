package com.andressalvatierra.programovil.features.movie.data.repository

import com.andressalvatierra.programovil.features.movie.data.datasource.MovieLocalDataSource
import com.andressalvatierra.programovil.features.movie.data.datasource.MovieRemoteDataSource
import com.andressalvatierra.programovil.features.movie.data.mapper.toEntityList
import com.andressalvatierra.programovil.features.movie.data.mapper.toModel
import com.andressalvatierra.programovil.features.movie.domain.model.MovieModel
import com.andressalvatierra.programovil.features.movie.domain.repository.IMoviesRepository

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : IMoviesRepository {
    override suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        val remoteResult = remoteDataSource.fetchPopularMovies()

        remoteResult.onSuccess { moviesDto ->
            localDataSource.saveMovies(moviesDto.toEntityList())
        }

        return remoteResult.map { moviesDto ->
            moviesDto.map { it.toModel() }
        }
    }
}