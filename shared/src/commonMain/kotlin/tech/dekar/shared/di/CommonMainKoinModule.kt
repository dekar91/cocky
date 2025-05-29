package tech.dekar.shared.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import tech.dekar.shared.ui.control.ActivityActionBus
import tech.dekar.shared.ui.control.NavigationBus

object CommonMainKoinModule {
    val commonMainModule = module {
        singleOf(::NavigationBus)
        singleOf(::ActivityActionBus)

    }
}