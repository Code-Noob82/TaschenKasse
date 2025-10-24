package de.bytehandwerk.taschenkasse.model

import kotlinx.serialization.Serializable

/**
 * Datenmodell für einen einzelnen Kassenartikel.
 *
 * @param id Eindeutige ID (z.B. 0-19 für die 20 Tasten).
 * @param name Anzeigename des Artikels (z.B. "Bier 0,5l").
 * @param priceInCents Preis in Cents, um Fließkommafehler zu vermeiden. (z.B. 450 für 4,50 €).
 */
@Serializable
data class Article(
    val id: Int,
    val name: String,
    val priceInCents: Long
)