package de.bytehandwerk.taschenkasse

import android.app.Application
import de.bytehandwerk.taschenkasse.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialisiere Koin beim App-Start
        initKoin {
            androidLogger() // Android-spezifisches Logging
            androidContext(this@MainApplication) // Stellt den Context bereit
        }
    }
}