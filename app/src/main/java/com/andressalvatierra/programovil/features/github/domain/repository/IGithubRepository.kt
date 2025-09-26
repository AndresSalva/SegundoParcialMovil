package com.andressalvatierra.programovil.features.github.domain.repository

import com.andressalvatierra.programovil.features.github.domain.model.UserModel

interface IGithubRepository {
    suspend fun findByNick(value: String): Result<UserModel>
}