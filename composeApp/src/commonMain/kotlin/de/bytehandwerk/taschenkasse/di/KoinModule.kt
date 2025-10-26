package de.bytehandwerk.taschenkasse.di

import de.bytehandwerk.taschenkasse.repository.ArticleRepository
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// Erwartet eine plattformspezifische Implementierung (Android/iOS)
// die uns die Multiplatform-Settings-Instanz gibt.
expect fun platformSettingsModule(): Module

/**
 * Das gemeinsame Koin-Modul für die App-Logik (commonMain).
 */
val commonModule = module {

    // JSON-Serialisierer (Singleton)
    single<Json> {
        Json {
            ignoreUnknownKeys = true // Ignoriert unbekannte Felder bei Deserialisierung
            prettyPrint = true       // Macht das gespeicherte JSON lesbarer
        }
    }

    // Repository (Singleton)
    single<ArticleRepository> {
        ArticleRepository(
            settings = get(), // Holt Settings aus dem platformSettingsModule
            json = get()      // Holt Json-Instanz
        )
    }

    // (Hier fügen wir später das ViewModel hinzu)
}

/**
 * Initialisierungs-Funktion, die von Android (Application-Klasse)
 * und iOS (main.ios.kt oder AppDelegate) aufgerufen wird.
 */
fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            commonModule,
            platformSettingsModule() // Fügt das plattformspezifische Modul hinzu
        )
    }
}