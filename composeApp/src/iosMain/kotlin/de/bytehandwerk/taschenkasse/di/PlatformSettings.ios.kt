package de.bytehandwerk.taschenkasse.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

/**
 * iOS-Implementierung: Nutzt NSUserDefaults.
 * (Die 'multiplatform-settings' Basis-Bibliothek
 * reicht hierfür aus)
 */
actual fun platformSettingsModule(): Module = module {
    single<Settings> {
        NSUserDefaultsSettings(
            delegate = NSUserDefaults.standardUserDefaults
        )
    }
}
