package com.andressalvatierra.programovil.features.dollar.domain.model

data class DollarModel(
    var dollarOfficial: String? = null,
    var dollarParallel: String? = null,
    var dollarUsdt: String? = null,
    var dollarUsdc: String? = null,
    var lastUpdated: String? = null

)