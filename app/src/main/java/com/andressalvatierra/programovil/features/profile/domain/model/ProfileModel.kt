package com.andressalvatierra.programovil.features.profile.domain.model

data class ProfileModel(
    val pathUrl: ProfileUrl,
    val name: ProfileName,
    val email: ProfileEmail,
    val cellphone: ProfileCellphone,
    val summary: ProfileSummary
)