package tech.dekar.cocky.shared.ui.control

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.CreateRecipeScreen
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.RecipeCreatedScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    navigationBus: NavigationBus,
) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            CreateRecipeScreen(
                onSave = { _ ->
                    navController.navigate(Screens.FinishedRecipe.route)
                },
                initialRecipe = null,
                onCancel = {}
            )
        }

        composable(Screens.CreateRecipe.route) {
            CreateRecipeScreen(
                onSave = { _ ->
                    navController.navigate(Screens.FinishedRecipe.route)
                },
                initialRecipe = null,
                onCancel = {}
            )
        }

        composable(Screens.FinishedRecipe.route) {
            RecipeCreatedScreen()
        }
    }
}
