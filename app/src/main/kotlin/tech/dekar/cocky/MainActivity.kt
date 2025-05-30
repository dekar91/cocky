package tech.dekar.cocky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.android.inject
import tech.dekar.cocky.shared.db.CockyDatabase
import tech.dekar.cocky.shared.ui.control.ActivityActionBus
import tech.dekar.cocky.shared.ui.control.Navigation
import tech.dekar.cocky.shared.ui.control.NavigationBus

class MainActivity : ComponentActivity() {
    val activityActionBus: ActivityActionBus by inject()
    val navigationBus: NavigationBus by inject()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(
              rememberNavController(),
                navigationBus)
        }

        val driver: SqlDriver = AndroidSqliteDriver(CockyDatabase.Schema, applicationContext, "test.db")
    }
}
