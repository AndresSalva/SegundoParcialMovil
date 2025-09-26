package com.andressalvatierra.programovil.features.dollar.data.repository

import com.andressalvatierra.programovil.features.dollar.data.datasource.DollarLocalDataSource
import com.andressalvatierra.programovil.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.andressalvatierra.programovil.features.dollar.domain.model.DollarModel
import com.andressalvatierra.programovil.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
// --- INICIO DE MODIFICACIÓN ---
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DollarRepository(
    val realTimeRemoteDataSource: RealTimeRemoteDataSource,
    val localDataSource: DollarLocalDataSource
) : IDollarRepository {

    override suspend fun getDollar(): Flow<DollarModel> {
        return realTimeRemoteDataSource.getDollarUpdates()
            .onEach {

                localDataSource.insert(it)
            }

            .map { dollarModelFromFirebase ->
                // Creamos la fecha formateada aquí mismo
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                val formattedDate = "Actualizado: " + sdf.format(Date(System.currentTimeMillis()))

                // Devolvemos una copia del modelo, pero ahora con la fecha incluida
                dollarModelFromFirebase.apply {
                    lastUpdated = formattedDate
                }
            }
    }
}