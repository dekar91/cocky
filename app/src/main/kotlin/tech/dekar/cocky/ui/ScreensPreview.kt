package tech.dekar.cocky.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tech.dekar.shared.ui.CockyTheme
import tech.dekar.shared.ui.CreateRecipeScreen

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