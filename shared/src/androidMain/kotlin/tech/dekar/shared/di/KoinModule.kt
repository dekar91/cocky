package tech.dekar.shared.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tech.dekar.shared.di.DispatchersTypes.IO
import tech.dekar.shared.di.DispatchersTypes.MAIN

object KoinModule {
    val androidMainModule = module {
        single<CoroutineDispatcher>(named(MAIN)) {Dispatchers.Main }
        single<CoroutineDispatcher>(named(IO)) {  Dispatchers.IO  }
        single<DekarClock> { StandardClock() }
        single<Logger> { AndroidLogger() }

    }
}