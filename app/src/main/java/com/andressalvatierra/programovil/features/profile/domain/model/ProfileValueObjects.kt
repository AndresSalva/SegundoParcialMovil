package com.andressalvatierra.programovil.features.profile.domain.model


@JvmInline
value class ProfileName(val value: String) {
    init {
        require(value.isNotBlank()) { "Name cannot be blank" }
    }
}

@JvmInline
value class ProfileEmail(val value: String) {
    init {

        val emailRegex = Regex("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,}))?$")
        require(value.isNotBlank() && emailRegex.matches(value)) { "Invalid email format" }
    }
}

@JvmInline
value class ProfileCellphone(val value: String) {
    init {
        require(value.isNotBlank()) { "Cellphone cannot be blank" }
    }
}

@JvmInline
value class ProfileUrl(val value: String) {
    init {
        // Se reemplaza la validación de Android por una comprobación simple.
        require(value.isNotBlank() && (value.startsWith("http://") || value.startsWith("https://"))) { "Invalid URL format" }
    }
}

@JvmInline
value class ProfileSummary(val value: String) {
    init {
        require(value.length <= 200) { "Summary cannot exceed 200 characters" }
    }
}