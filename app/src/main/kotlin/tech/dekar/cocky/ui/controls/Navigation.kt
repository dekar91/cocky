package tech.dekar.cocky.ui.controls

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import tech.dekar.cocky.configs.Utils.logTag
import tech.dekar.cocky.recepie.RecipeCreatorScreen
import tech.dekar.cocky.recepie.RecipeViewModel
import tech.dekar.shared.ui.CreateRecipeScreen
import tech.dekar.shared.ui.control.NavigationBus
import tech.dekar.shared.ui.control.NavigationScreens

@Composable
fun Navigation(
//    viewModel: MainViewModel,
//    settingsViewModel: SettingsViewModel,
    navigationBus: NavigationBus,

    navController: NavHostController = rememberNavController()
) {
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            navigationBus.actions.collect { target ->
                Log.d(logTag(), "Collected navigation action: $target")

                when (target) {
                    NavigationScreens.Home -> navController.navigate(NavigationScreens.Home.route) {
                        popUpTo(0)
                    }

                    NavigationScreens.CreatedRecipe -> navController.navigate(NavigationScreens.CreatedRecipe.route)
                }
            }
        }

        onDispose {
            Log.d(logTag(), "Disposing navigation effect")
            job.cancel()
        }
    }

    NavHost(navController = navController, startDestination = NavigationScreens.Home.route) {
        composable(NavigationScreens.Home.route) {
            val viewModel = koinViewModel<RecipeViewModel>()
            CreateRecipeScreen(
                onSave = {},
                onCancel = {}
            )
//            RecipeCreatorScreen(viewModel)
        }

        composable(NavigationScreens.CreatedRecipe.route) {
            val viewModel = koinViewModel<RecipeViewModel>()
            RecipeCreatorScreen(viewModel)
        }
    }
}
