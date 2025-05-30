package tech.dekar.cocky.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tech.dekar.cocky.shared.ui.CockyTheme
import tech.dekar.cocky.shared.domain.usecases.recipes.screens.CreateRecipeScreen

@Composable
@Preview()
fun CreateRecipePreview() {
    CockyTheme {
        CreateRecipeScreen(
            onSave = {},
            onCancel = {}
        )
    }
}
