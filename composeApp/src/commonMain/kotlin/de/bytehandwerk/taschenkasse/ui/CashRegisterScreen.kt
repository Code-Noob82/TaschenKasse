package de.bytehandwerk.taschenkasse.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.bytehandwerk.taschenkasse.viewModel.CashRegisterViewModel

/**
 * Der Hauptbildschirm (Tablet-Layout).
 * Zeigt links das Artikel-Grid und rechts den Warenkorb.
 */
@Composable
fun CashRegisterScreen(viewModel: CashRegisterViewModel) {
    // Abonniere die Zustände aus dem ViewModel
    val articles by viewModel.articles.collectAsState()
    val cart by viewModel.cartState.collectAsState()

    Row(Modifier.fillMaxSize()) {

        // --- Linke Spalte: Artikel-Grid ---
        // (Hier wäre eine LazyVerticalGrid ideal)
        Column(Modifier.weight(2f).padding(8.dp)) {
            Text("Artikel (Anzahl: ${articles.size})", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            // Platzhalter für das Artikel-Grid
            articles.take(5).forEach { article ->
                Button(
                    onClick = { viewModel.onArticleClicked(article) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("${article.name} (${formatPrice(article.priceInCents)})")
                }
            }
            Spacer(Modifier.height(16.dp))
            // Platzhalter für Mengen-Vorwahl
            Row {
                (1..3).forEach { qty ->
                    Button(onClick = { viewModel.onQuantitySelected(qty) }, Modifier.padding(4.dp)) {
                        Text("x$qty")
                    }
                }
                if (cart.quantityMultiplier > 1) {
                    Text("Menge: ${cart.quantityMultiplier}x")
                }
            }
        }

        // --- Rechte Spalte: Warenkorb & Summe ---
        Column(Modifier.weight(1f).padding(16.dp)) {
            Text("Warenkorb", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            // Platzhalter für Warenkorb-Liste
            cart.items.takeLast(5).forEach { item ->
                Text("+ ${item.name}")
            }
            if (cart.items.size > 5) Text("... und ${cart.items.size - 5} weitere")

            Spacer(Modifier.height(32.dp))

            // Summe
            Text(
                "GESAMT: ${formatPrice(cart.totalInCents)}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            // Reset Button
            Button(onClick = { viewModel.onResetClicked() }) {
                Text("Neuer Kunde")
            }
        }
    }
}

// Hilfsfunktion zur Preisformatierung (KMP-kompatibel)
private fun formatPrice(cents: Long): String {
    val euro = cents / 100
    val remainingCents = cents % 100
    // Führende Null für Cents hinzufügen (z.B. 1,05 € statt 1,5 €)
    val centsStr = remainingCents.toString().padStart(2, '0')
    return "$euro,$centsStr €"
}
