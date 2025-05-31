package tech.dekar.cocky.desktop.di


import app.cash.sqldelight.db.QueryResult
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
import java.io.File

object DesktopKoinModule {
    val desktopModule = module {
        single<CoroutineDispatcher>(named(MAIN)) { Dispatchers.Swing }
        single<CoroutineDispatcher>(named(IO)) { Dispatchers.IO }

        single<SqlDriver> {
            val dbPath = System.getProperty("user.home") + "/.cocky/cocky.db"
            val dbFile = File(dbPath)
            dbFile.parentFile?.mkdirs()
            JdbcSqliteDriver("jdbc:sqlite:$dbPath")

        }

        single<CockyDatabase> {
            val driver: SqlDriver = get()
            driver.execute(null, "PRAGMA journal_mode=DELETE;", 0)

            try {
                val count = driver.executeQuery<Int>(
                    1,
                    "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='recipes';",
                    mapper = { cursor ->
                        QueryResult.Value(cursor.getLong(0)?.toInt() ?: 0)
                    }, parameters = 0
                ).value

                if (count == 0) {
                    CockyDatabase.Schema.create(driver)
                    driver.execute(
                        null,
                        "PRAGMA user_version = ${CockyDatabase.Schema.version};",
                        0
                    )
                    println("✅ Schema created.")
                } else {
                    println("✅ Schema already exists.")
                }

            } catch (e: Exception) {
                println("❌ Error during DB setup: ${e.message}")
            }

            CockyDatabase(driver, get())
        }
    }
}
