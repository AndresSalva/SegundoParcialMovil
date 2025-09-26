package com.andressalvatierra.programovil.features.dollar.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun CurrencyCard(currencyName: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currencyName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Bs. $value",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), // Padding para que las tarjetas no toquen los bordes
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                val data = stateValue.data

                // Título de la pantalla
                Text(
                    text = "Tipo de Cambio",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Usamos nuestro nuevo Composable para cada valor
                CurrencyCard(currencyName = "Oficial", value = data.dollarOfficial ?: "N/A")
                CurrencyCard(currencyName = "Paralelo", value = data.dollarParallel ?: "N/A")
                CurrencyCard(currencyName = "USDT", value = data.dollarUsdt ?: "N/A")
                CurrencyCard(currencyName = "USDC", value = data.dollarUsdc ?: "N/A")

                Spacer(modifier = Modifier.height(24.dp)) // Un espacio antes de la fecha

                // Mostramos la fecha de actualización
                Text(
                    text = data.lastUpdated ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
