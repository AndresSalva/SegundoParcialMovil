package com.andressalvatierra.programovil.features.dollar.domain.repository

import com.andressalvatierra.programovil.features.dollar.domain.model.DollarModel
import kotlinx.coroutines.flow.Flow

interface IDollarRepository {
    suspend fun getDollar(): Flow<DollarModel>
}