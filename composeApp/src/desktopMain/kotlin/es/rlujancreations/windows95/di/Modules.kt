package es.rlujancreations.windows95.di

import es.rlujancreations.windows95.desktop.DesktopViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 30/12/24.
 */
val desktopModules = module {
    viewModelOf(::DesktopViewModel)
}