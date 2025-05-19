package tech.dekar.cocky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import tech.dekar.cocky.ui.controls.ActivityActionBus
import tech.dekar.cocky.ui.controls.Navigation
import tech.dekar.lockme.NavigationBus
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var activityActionBus: ActivityActionBus

    @Inject lateinit var navigationBus: NavigationBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(navigationBus)
        }
    }
}
