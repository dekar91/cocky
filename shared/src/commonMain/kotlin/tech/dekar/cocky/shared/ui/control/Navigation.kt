package tech.dekar.cocky.shared.ui.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import tech.dekar.cocky.shared.ui.CreateRecipeScreen
import tech.dekar.cocky.shared.ui.RecipeCreatedScreen

sealed class Screen {
    object Home : Screen()
    object CreateRecipe : Screen()
    object FinishedRecipe : Screen()
}

@Composable
fun Navigation(
    navigationBus: NavigationBus,
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            navigationBus.actions.collect { target ->
                currentScreen = target
            }
        }

        onDispose {
            job.cancel()
        }
    }

    when (currentScreen) {
        Screen.Home -> {
            CreateRecipeScreen(
                onSave = { _ ->
                    currentScreen = Screen.FinishedRecipe
                },
                initialRecipe = null,
                onCancel = {}
            )
        }
        Screen.FinishedRecipe -> {
            RecipeCreatedScreen()
        }
        Screen.CreateRecipe -> {
            CreateRecipeScreen(
                onSave = { _ ->
                    currentScreen = Screen.FinishedRecipe
                },
                initialRecipe = null,
                onCancel = {}
            )
        }
    }
}
