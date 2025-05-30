package tech.dekar.cocky.desktop.di


import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.swing.Swing
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tech.dekar.cocky.shared.db.CockyDatabase
import tech.dekar.cocky.shared.di.DispatchersTypes.IO
import tech.dekar.cocky.shared.di.DispatchersTypes.MAIN

object DesktopKoinModule {
    val desktopModule = module {
        single<CoroutineDispatcher>(named(MAIN)) { Dispatchers.Swing }
        single<CoroutineDispatcher>(named(IO)) { Dispatchers.IO }

        single<SqlDriver> {
//            val dbPath = System.getProperty("user.home") + "/.cocky/cocky.db"
//            val dbFile = File(dbPath)
//            dbFile.parentFile?.mkdirs()

            JdbcSqliteDriver("jdbc:sqlite:cocky.db").also { driver ->
                CockyDatabase.Schema.create(driver)
            }
        }

        single { CockyDatabase(get(), get()) }
    }
}
