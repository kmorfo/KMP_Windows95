package es.rlujancreations.windows95.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import es.rlujancreations.windows95.components.window.DraggableWindowViewModel
import es.rlujancreations.windows95.data.database.DatabaseFactory
import es.rlujancreations.windows95.data.database.FileRepositoryRoom
import es.rlujancreations.windows95.data.database.WindowsDatabase
import es.rlujancreations.windows95.desktop.DesktopViewModel
import es.rlujancreations.windows95.domain.FileRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 30/12/24.
 */
val desktopModules = module {
    single { DatabaseFactory() }
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<WindowsDatabase>().fileDao() }

    singleOf(::FileRepositoryRoom).bind<FileRepository>()

    viewModelOf(::DesktopViewModel)
    viewModelOf(::DraggableWindowViewModel)
}