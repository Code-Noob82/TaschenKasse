package de.bytehandwerk.taschenkasse.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import de.bytehandwerk.taschenkasse.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class ArticleRepository(
private val settings: Settings,
private val json: Json
) {
    // Der Key, unter dem die gesamte Artikelliste als JSON gespeichert wird.
    private val ARTICLES_KEY = "ARTICLES_V1"

    // Ein In-Memory-Flow, der die geladenen Artikel hält und die UI versorgt.
    private val _articlesFlow = MutableStateFlow<List<Article>>(emptyList())
    val articlesFlow: Flow<List<Article>> = _articlesFlow.asStateFlow()

    init {
        // Beim Start laden wir die Artikel sofort aus dem Speicher.
        loadArticlesFromSettings()
    }

    private fun loadArticlesFromSettings() {
        // --- KORREKTUR ---
        // Erstelle einen Serializer für eine Liste von Artikeln.
        val articlesSerializer = ListSerializer(Article.serializer())

        // Verwende diesen Serializer beim Dekodieren.
        val articles: List<Article>? = settings.decodeValueOrNull(articlesSerializer, ARTICLES_KEY)

        if (articles.isNullOrEmpty()) {
            _articlesFlow.value = createDefaultArticles()
        } else {
            _articlesFlow.value = articles
        }
    }

    /**
     * Speichert einen einzelnen (geänderten) Artikel und aktualisiert die Liste.
     * Dies wird für die "Long-Press-Config" benötigt (Roadmap-Punkt 2).
     */
    fun saveArticle(updatedArticle: Article) {
        _articlesFlow.update { currentList ->
            val newList = currentList.map {
                if (it.id == updatedArticle.id) updatedArticle else it
            }

            // --- KORREKTUR ---
            // Erstelle auch hier den richtigen Serializer.
            val articlesSerializer = ListSerializer(Article.serializer())

            // Speichere die *gesamte* neue Liste mit dem korrekten Serializer.
            settings.encodeValue(articlesSerializer, ARTICLES_KEY, newList)

            newList
        }
    }


    /**
     * Erstellt 20 leere Artikel-Tasten als Standard-Setup.
     */
    private fun createDefaultArticles(): List<Article> {
        return (0..19).map { index ->
            // Platzhalter-Artikel
            Article(
                id = index,
                name = "Artikel ${index + 1}",
                priceInCents = 100L // 1,00 € als Standard
            )
        }
    }
}