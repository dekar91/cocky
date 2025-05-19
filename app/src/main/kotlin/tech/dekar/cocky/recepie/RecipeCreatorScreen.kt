package tech.dekar.cocky.recepie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.dekar.cocky.ui.screens.BaseScreen

@Composable
fun RecipeCreatorScreen(viewModel: RecipeViewModel = viewModel()) {
    val title by viewModel.title.collectAsState()
    val ingredients by viewModel.ingredients.collectAsState()
    val steps by viewModel.steps.collectAsState()
    val imageUrl by viewModel.imageUrl.collectAsState()
    val youtubeUrl by viewModel.youtubeUrl.collectAsState()

    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { viewModel.updateTitle(it) },
                label = { Text("Название рецепта") },
                modifier = Modifier.fillMaxWidth()
            )
            ingredients.forEachIndexed { index, ingredient ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = ingredient.first,
                        onValueChange = { viewModel.updateIngredient(index, it, ingredient.second) },
                        label = { Text("Ингредиент") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = ingredient.second,
                        onValueChange = { viewModel.updateIngredient(index, ingredient.first, it) },
                        label = { Text("Количество") },
                        modifier = Modifier.weight(1f)
                    )
                    Button(onClick = { viewModel.removeIngredient(index) }) {
                        Text("Удалить")
                    }
                }
            }
            Button(onClick = { viewModel.addIngredient() }) {
                Text("Добавить ингредиент")
            }
            steps.forEachIndexed { index, step ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = step,
                        onValueChange = { viewModel.updateStep(index, it) },
                        label = { Text("Шаг ${index + 1}") },
                        modifier = Modifier.weight(1f)
                    )
                    Button(onClick = { viewModel.removeStep(index) }) {
                        Text("Удалить")
                    }
                }
            }
            Button(onClick = { viewModel.addStep() }) {
                Text("Добавить шаг")
            }
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { viewModel.updateImageUrl(it) },
                label = { Text("Ссылка на изображение (опционально)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = youtubeUrl,
                onValueChange = { viewModel.updateYoutubeUrl(it) },
                label = { Text("Ссылка на YouTube (опционально)") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { viewModel.saveRecipe() }, modifier = Modifier.fillMaxWidth()) {
                Text("Сохранить рецепт")
            }
        }
    }
}

@Preview
@Composable
fun RecipeCreatorScreenPreview() {
    RecipeCreatorScreen()
}
