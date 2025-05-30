package tech.dekar.cocky.desktop

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import tech.dekar.cocky.desktop.di.DesktopKoinModule.desktopModule
import tech.dekar.cocky.shared.di.CommonMainKoinModule.commonMainModule
import tech.dekar.cocky.shared.ui.control.DesktopNavigation
import tech.dekar.cocky.shared.ui.control.NavigationBus

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            val navigationBus: NavigationBus = koinInject()
            DesktopNavigation(navigationBus)
        }
    }
}

fun main() {
    startKoin {
        modules(commonMainModule, desktopModule)
    }
    
    application {
        Window(onCloseRequest = ::exitApplication, title = "KMP Desktop") {
            App()
        }
    }
}
