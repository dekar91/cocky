package tech.dekar.cocky.desktop

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import tech.dekar.cocky.shared.ui.CreateRecipeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        CreateRecipeScreen(
            onSave = {},
            onCancel = {}
        )

//        var counter by remember { mutableStateOf(0) }
//        Button(onClick = { counter++ }) {
//            Text("Clicked $counter times")
//        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP Desktop") {
        App()
    }
}
