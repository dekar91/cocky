package tech.dekar.cocky.shared.ui

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

@Composable
fun RecipeCreatedScreen() {
    BaseScreen(
        content =  {
            Text("Recipe created")
        }
    )
}