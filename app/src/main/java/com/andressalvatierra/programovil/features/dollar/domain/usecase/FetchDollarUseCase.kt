package com.andressalvatierra.programovil.features.dollar.domain.usecase

import com.andressalvatierra.programovil.features.dollar.domain.model.DollarModel
import com.andressalvatierra.programovil.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow

class FetchDollarUseCase(
    val repository: IDollarRepository
) {

    suspend fun invoke(): Flow<DollarModel> {
        return repository.getDollar()
    }
}