package de.bytehandwerk.taschenkasse.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.bytehandwerk.taschenkasse.model.Article

/**
 * Zeigt die Liste der Artikel im Warenkorb.
 */
@Composable
internal fun CartList(
    modifier: Modifier = Modifier,
    cart: List<Article>,
    onRemoveClick: (Article) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                "Warenkorb",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            HorizontalDivider()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (cart.isEmpty()) {
                    item {
                        Text(
                            "Warenkorb ist leer",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
                items(cart, key = { it.id }) { article ->
                    // TODO: Hier später Mengenzählung einbauen (Roadmap-Punkt 2)
                    CartItemRow(article, onRemoveClick)
                }
            }
        }
    }
}