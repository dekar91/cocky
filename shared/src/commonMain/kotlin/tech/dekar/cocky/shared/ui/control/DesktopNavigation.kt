package tech.dekar.cocky.shared.ui.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.runBlocking
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.CreateRecipeScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.RecipeCreatedScreen

@Composable
fun DesktopNavigation(navigationBus: NavigationBus) {
    val currentScreen by navigationBus.actions.collectAsState(initial = Screens.Home)

    when (currentScreen) {
        Screens.Home -> CreateRecipeScreen(
            onSave = { _ ->
                runBlocking {  navigationBus.emit(Screens.FinishedRecipe) }
            },
            initialRecipe = null,
            onCancel = {}
        )
        Screens.CreateRecipe -> CreateRecipeScreen(
            onSave = { _ ->
                runBlocking {  navigationBus.emit(Screens.FinishedRecipe) }
            },
            initialRecipe = null,
            onCancel = {}
        )
        Screens.FinishedRecipe -> RecipeCreatedScreen()
    }
}
