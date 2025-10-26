package de.bytehandwerk.taschenkasse.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.Settings
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Android-Implementierung: Nutzt DataStore.
 * (Stellt sicher, dass du 'multiplatform-settings-datastore'
 * in 'androidMain.dependencies' im build.gradle hast)
 */
@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
actual fun platformSettingsModule(): Module = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = {
                androidContext().preferencesDataStoreFile("taschenkasse_settings")
            }
        )
    }
    single<Settings> {
        DataStoreSettings(
            datastore = get()) as Settings // Koin stellt den Android-Context bereit
    }
}