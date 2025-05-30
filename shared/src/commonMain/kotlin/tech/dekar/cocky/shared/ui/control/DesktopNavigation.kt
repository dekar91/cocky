package tech.dekar.cocky.shared.ui.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.CreateRecipeScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.RecipeCreatedScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.RecipesListScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.viewmodel.RecipesViewModel

class DesktopNavigationComponent : KoinComponent {
    val viewModel: RecipesViewModel by inject()
}

@Composable
fun DesktopNavigation(navigationBus: NavigationBus) {
    val currentScreen by navigationBus.actions.collectAsState(initial = Screens.RecipesList)
    val component = remember { DesktopNavigationComponent() }

    when (currentScreen) {
        Screens.RecipesList -> RecipesListScreen(
            viewModel = component.viewModel,
            onCreateRecipe = { runBlocking { navigationBus.emit(Screens.CreateRecipe) } }
        )

        Screens.CreateRecipe -> CreateRecipeScreen(
            viewModel = component.viewModel,
            onCancel = { runBlocking { navigationBus.emit(Screens.RecipesList) } }
        )

        Screens.RecipeCreated -> RecipeCreatedScreen()
    }
}
