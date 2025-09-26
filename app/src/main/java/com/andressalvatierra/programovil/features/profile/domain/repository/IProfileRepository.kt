package com.andressalvatierra.programovil.features.profile.domain.repository

import com.andressalvatierra.programovil.features.profile.domain.model.ProfileModel

interface IProfileRepository {
    fun fetchData(): Result<ProfileModel>
}