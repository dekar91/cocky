package tech.dekar.cocky.shared.ui.control

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    data object Home : Screens() {
        const val route = "home"
    }
    data object CreateRecipe : Screens() {
        const val route = "create_recipe"
    }
    data object FinishedRecipe : Screens() {
        const val route = "finished_recipe"
    }
}