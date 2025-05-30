package tech.dekar.cocky.shared.ui.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.CreateRecipeScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.RecipeCreatedScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.RecipesListScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.viewmodel.RecipesViewModel

class NavigationComponent : KoinComponent {
    val viewModel: RecipesViewModel by inject()
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    navigationBus: NavigationBus,
) {
    val component = remember { NavigationComponent() }

    LaunchedEffect(navigationBus) {
        navigationBus.actions.collect { screen ->
            navController.navigate(screen.route)
        }
    }

    NavHost(navController = navController, startDestination = Screens.RecipesList.route) {
        composable(Screens.RecipesList.route) {
            RecipesListScreen(
                viewModel = component.viewModel,
                onCreateRecipe = { runBlocking { navigationBus.emit(Screens.CreateRecipe) } }
            )
        }

        composable(Screens.CreateRecipe.route) {
            CreateRecipeScreen(
                viewModel = component.viewModel,
                onCancel = { runBlocking { navigationBus.emit(Screens.RecipesList) } }
            )
        }

        composable(Screens.RecipeCreated.route) {
            RecipeCreatedScreen()
        }
    }
}
