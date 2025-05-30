package tech.dekar.cocky.shared.domain.usecases.recipes.screens

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import tech.dekar.cocky.shared.ui.screens.BaseScreen

@Composable
fun RecipeCreatedScreen() {
    BaseScreen(
        content = {
            Text("Recipe created")
        }
    )
}