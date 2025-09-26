package com.andressalvatierra.programovil.features.dollar.data.mapper

import com.andressalvatierra.programovil.features.dollar.data.database.entity.DollarEntity
import com.andressalvatierra.programovil.features.dollar.domain.model.DollarModel
// --- INICIO DE MODIFICACIÓN ---
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private fun formatTimestamp(timestamp: Long): String {
    if (timestamp == 0L) return "Fecha no disponible"
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return "Actualizado: " + sdf.format(Date(timestamp))
}


fun DollarEntity.toModel(): DollarModel {
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarUsdt = dollarUsdt,
        dollarUsdc = dollarUsdc,

        lastUpdated = formatTimestamp(timestamp)

    )
}

fun DollarModel.toEntity(): DollarEntity {
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarUsdt = dollarUsdt,
        dollarUsdc = dollarUsdc,

        timestamp = System.currentTimeMillis()

    )
}