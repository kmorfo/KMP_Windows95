package es.rlujancreations.windows95.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by Raúl L.C. on 30/12/24.
 */

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin{
        config?.invoke(this)
        modules(
            desktopModules
        )
    }
}