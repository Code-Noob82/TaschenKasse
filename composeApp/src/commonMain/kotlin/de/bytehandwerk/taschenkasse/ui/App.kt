package de.bytehandwerk.taschenkasse.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import de.bytehandwerk.taschenkasse.viewModel.CashRegisterViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

/**
 * Der gemeinsame Compose-Einstiegspunkt für alle Plattformen.
 */
@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun App() {
    // KoinContext umschließt die App, um Injections zu ermöglichen
    KoinContext {
        // Wir verwenden koinInject(), um das ViewModel zu erhalten.
        // Koin verwaltet den Lebenszyklus.
        val viewModel: CashRegisterViewModel = koinInject()

        MaterialTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("TaschenKasse") }
                    )
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    CashRegisterScreen(viewModel)
                }
            }
        }
    }
}
