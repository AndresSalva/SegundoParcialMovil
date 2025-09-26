package com.andressalvatierra.programovil.features.movie.data.datasource

import com.andressalvatierra.programovil.features.movie.data.api.MovieService
import com.andressalvatierra.programovil.features.movie.data.api.dto.MovieDto

class MovieRemoteDataSource(
    private val movieService: MovieService,
    private val apiKey: String
) {
    suspend fun fetchPopularMovies(): Result<List<MovieDto>> {
        return try {
            val response = movieService.fetchPopularMovies(apiKey = apiKey)
            if (response.isSuccessful) {
                val moviePage = response.body()
                Result.success(moviePage?.results ?: emptyList())
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}