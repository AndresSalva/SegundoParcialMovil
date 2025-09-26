package com.andressalvatierra.programovil.features.movie.data.mapper

import com.andressalvatierra.programovil.features.movie.data.api.dto.MovieDto
import com.andressalvatierra.programovil.features.movie.data.database.entity.MovieEntity
import com.andressalvatierra.programovil.features.movie.domain.model.MovieModel

fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        pathUrl = this.pathUrl,
        title = this.title
    )
}

fun List<MovieDto>.toEntityList(): List<MovieEntity> {
    return this.map { it.toEntity() }
}

fun MovieDto.toModel(): MovieModel {
    return MovieModel(
        id = this.id,
        pathUrl = "https://image.tmdb.org/t/p/w185" + this.pathUrl,
        title = this.title
    )
}