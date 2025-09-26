package com.andressalvatierra.programovil.features.profile.data.repository

import com.andressalvatierra.programovil.features.profile.domain.model.*
import com.andressalvatierra.programovil.features.profile.domain.repository.IProfileRepository

class ProfileRepository: IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return try {
            Result.success(
                ProfileModel(
                    name = ProfileName("Homero J. Simpson"),
                    email = ProfileEmail("homero.simpson@springfieldmail.com"),
                    cellphone = ProfileCellphone("+1 (939) 555‑7422"),
                    pathUrl = ProfileUrl("https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg"),
                    summary = ProfileSummary("Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear.")
                )
            )
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }
}