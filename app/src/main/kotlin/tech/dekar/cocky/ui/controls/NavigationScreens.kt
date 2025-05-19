package tech.dekar.cocky.ui.controls

sealed class NavigationScreens(val route: String) {
    object Home : NavigationScreens(route = "home")
    object CreatedRecipe : NavigationScreens(route = "createdRecipe")
}
