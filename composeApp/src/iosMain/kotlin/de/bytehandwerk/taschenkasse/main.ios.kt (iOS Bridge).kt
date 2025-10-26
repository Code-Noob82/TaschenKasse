package de.bytehandwerk.taschenkasse

import androidx.compose.ui.window.ComposeUIViewController
import de.bytehandwerk.taschenkasse.di.initKoin
import de.bytehandwerk.taschenkasse.ui.App
import platform.UIKit.UIViewController

/**
 * Erstellt den UIViewController, der die Compose 'App' hostet.
 * Wird von SwiftUI (ContentView.swift) aufgerufen.
 */
/**
 * Erstellt den UIViewController, der die Compose 'App' hostet.
 * Wird von SwiftUI (ContentView.swift) aufgerufen.
 */
fun createMainViewController(): UIViewController {
    // Initialisiere Koin für iOS
    // (Muss vor dem ersten Koin-Aufruf passieren)
    initKoin {
        // Hier könnten iOS-spezifische Koin-Settings hin
    }

    return ComposeUIViewController { App() }
}
