package com.andressalvatierra.programovil.features.profile.domain.usecase

import com.andressalvatierra.programovil.features.profile.domain.model.ProfileModel
import com.andressalvatierra.programovil.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.delay

class GetProfileUseCase(
    val repository: IProfileRepository
) {
    suspend fun invoke(): Result<ProfileModel> {
        delay(3000)
        return repository.fetchData()
    }
}