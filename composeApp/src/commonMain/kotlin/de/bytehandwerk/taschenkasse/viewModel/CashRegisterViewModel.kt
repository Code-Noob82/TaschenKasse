package de.bytehandwerk.taschenkasse.viewModel

import de.bytehandwerk.taschenkasse.model.Article
import de.bytehandwerk.taschenkasse.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlin.coroutines.CoroutineContext

// Datenklasse für den Zustand des Warenkorbs
data class CartState(
    val items: List<Article> = emptyList(),
    val totalInCents: Long = 0L,
    val quantityMultiplier: Int = 1
)

/**
 * Das gemeinsame ViewModel. Es überlebt Konfigurationsänderungen (auf Android)
 * und wird von Koin verwaltet.
 */
class CashRegisterViewModel(
    private val articleRepository: ArticleRepository
) : CoroutineScope {

    // Eigener Scope für den ViewModel
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    // Zustand der Artikel-Tasten (die 20 Buttons)
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    // Zustand des Warenkorbs (Items, Summe, Mengen-Vorwahl)
    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    init {
        // Beim Starten des ViewModels, höre auf Änderungen aus dem Repository
        articleRepository.articlesFlow
            .onEach { _articles.value = it }
            .launchIn(this)
    }

    /**
     * Ein Artikel-Button wurde geklickt.
     */
    fun onArticleClicked(article: Article) {
        _cartState.update { current ->
            val newItems = current.items.toMutableList()
            // Füge den Artikel x-Mal hinzu (basierend auf der Vorwahl)
            repeat(current.quantityMultiplier) {
                newItems.add(article)
            }

            // Berechne neue Summe und setze Multiplikator zurück
            current.copy(
                items = newItems,
                totalInCents = newItems.sumOf { it.priceInCents },
                quantityMultiplier = 1 // WICHTIG: Nach Klick zurücksetzen
            )
        }
    }

    /**
     * Eine Mengen-Vorwahl-Taste (1-9) wurde geklickt.
     */
    fun onQuantitySelected(quantity: Int) {
        _cartState.update {
            it.copy(quantityMultiplier = quantity)
        }
    }

    /**
     * "Neuer Kunde" / Reset wurde geklickt.
     */
    fun onResetClicked() {
        _cartState.value = CartState() // Einfach den State zurücksetzen
    }

    /**
     * Ein Artikel wurde lange gedrückt und konfiguriert.
     */
    fun onArticleConfigSaved(updatedArticle: Article) {
        articleRepository.saveArticle(updatedArticle)
        // Das Repository-Flow-Update sorgt automatisch dafür, dass
        // der `articles` StateFlow in diesem ViewModel aktualisiert wird.
    }

    /**
     * Wird aufgerufen, wenn das ViewModel zerstört wird (z.B. App-Schließung).
     */
    fun onClear() {
        job.cancel()
    }
}