package tech.dekar.cocky.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tech.dekar.cocky.configs.SentryConfig
import tech.dekar.cocky.recepie.RecipeViewModel
import tech.dekar.shared.di.DispatchersTypes.IO
import tech.dekar.shared.di.DispatchersTypes.MAIN
import tech.dekar.shared.di.KoinModule.androidMainModule

object KoinAndroidModule {

    val androidModule = module {
        includes(androidMainModule)

        viewModel { RecipeViewModel(get(named(MAIN)), get(named(IO))) }

        singleOf<SentryConfig>(::SentryConfig)
    }
}
