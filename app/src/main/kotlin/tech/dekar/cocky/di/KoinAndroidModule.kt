package tech.dekar.cocky.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tech.dekar.cocky.configs.SentryConfig
import tech.dekar.cocky.recepie.RecipeViewModel
import tech.dekar.cocky.shared.db.CockyDatabase
import tech.dekar.cocky.shared.di.DispatchersTypes.IO
import tech.dekar.cocky.shared.di.DispatchersTypes.MAIN
import tech.dekar.cocky.shared.di.KoinModule.androidMainModule

object KoinAndroidModule {

    val androidModule = module {
        includes(androidMainModule)

        single<SqlDriver> {
            AndroidSqliteDriver(CockyDatabase.Schema, get(), "cocky.db")
        }

        single {
            val driver = get<SqlDriver>()
            CockyDatabase(driver, get())
        }

        viewModel { RecipeViewModel(get(named(MAIN)), get(named(IO))) }

        singleOf<SentryConfig>(::SentryConfig)
    }
}
