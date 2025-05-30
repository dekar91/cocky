package tech.dekar.cocky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import tech.dekar.cocky.shared.ui.control.Navigation
import tech.dekar.cocky.shared.ui.control.NavigationBus

class MainActivity : ComponentActivity() {
    private val navigationBus: NavigationBus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(
                navController = rememberNavController(),
                navigationBus = navigationBus
            )
        }
    }
}
