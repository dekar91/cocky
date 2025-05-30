package tech.dekar.cocky.desktop

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import tech.dekar.cocky.shared.ui.control.DesktopNavigation
import tech.dekar.cocky.shared.ui.control.NavigationBus

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navigationBus = remember { NavigationBus() }
        DesktopNavigation(navigationBus)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP Desktop") {
        App()
    }
}
