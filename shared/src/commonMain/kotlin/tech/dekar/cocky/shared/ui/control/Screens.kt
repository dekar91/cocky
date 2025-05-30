package tech.dekar.cocky.shared.ui.control

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route: String) {
    data object RecipesList : Screens("recipes_list")
    data object CreateRecipe : Screens("create_recipe")
    data object RecipeCreated : Screens("recipe_created")
}