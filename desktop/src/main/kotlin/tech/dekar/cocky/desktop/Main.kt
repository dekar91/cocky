package tech.dekar.cocky.desktop

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import tech.dekar.cocky.shared.ui.control.Navigation
import tech.dekar.cocky.shared.ui.control.NavigationBus

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigation(NavigationBus())
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP Desktop") {
        App()
    }
}
