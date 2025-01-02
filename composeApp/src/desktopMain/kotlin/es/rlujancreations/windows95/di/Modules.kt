package es.rlujancreations.windows95.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import es.rlujancreations.windows95.data.database.DatabaseFactory
import es.rlujancreations.windows95.data.database.WindowsDatabase
import es.rlujancreations.windows95.desktop.DesktopViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 30/12/24.
 */
val desktopModules = module {

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<WindowsDatabase>().fileDao() }

    viewModelOf(::DesktopViewModel)
}