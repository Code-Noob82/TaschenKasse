package de.bytehandwerk.taschenkasse.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.bytehandwerk.taschenkasse.ui.composables.ArticleGrid
import de.bytehandwerk.taschenkasse.ui.composables.CartList
import de.bytehandwerk.taschenkasse.ui.composables.CheckoutArea
import de.bytehandwerk.taschenkasse.viewModel.CashRegisterViewModel
import org.koin.compose.koinInject

/**
 * Der Hauptbildschirm der Kasse, aufgeteilt in Artikel-Grid (links)
 * und Warenkorb/Summe (rechts).
 */
@Composable
fun CashRegisterScreen(
    viewModel: CashRegisterViewModel = koinInject()
) {
    val articles by viewModel.articles.collectAsState()
    val cartState by viewModel.cartState.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // --- Linke Spalte: Artikel-Grid (60% Breite) ---
        Box(modifier = Modifier.weight(3f).fillMaxHeight()) {
            ArticleGrid(
                articles = articles,
                onArticleClick = { viewModel.onArticleClicked(it) }
            )
        }

        // --- Rechte Spalte: Warenkorb & Summe (40% Breite) ---
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            // Warenkorb (füllt den oberen Bereich)
            CartList(
                modifier = Modifier.weight(1f),
                cart = cartState.items,
                onRemoveClick = { viewModel.onRemoveArticleClicked(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Summen/Checkout-Bereich (unten)
            CheckoutArea(
                totalInCents = cartState.totalInCents,
                onResetClick = { viewModel.onResetClicked() }
            )
        }
    }
}

/**
 * KMP-sichere Funktion zur Formatierung von Cents in einen Euro-String (z.B. "1,50 €").
 */
fun formatPrice(cents: Long): String {
    val euros = cents / 100
    val remainingCents = cents % 100

    // Führende Null für Cents hinzufügen, falls nötig (z.B. 1,05 €)
    val centsString = if (remainingCents < 10) "0$remainingCents" else remainingCents.toString()

    return "$euros,$centsString €"
}