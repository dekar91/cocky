package tech.dekar.cocky.shared.ui.control

sealed class NavigationScreens(val route: String) {
    object Home : NavigationScreens(route = "home")
    object CreatedRecipe : NavigationScreens(route = "createdRecipe")
}
