package tech.dekar.cocky.shared.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import tech.dekar.cocky.shared.domain.model.Recipe
import tech.dekar.cocky.shared.generated.resources.Res
import tech.dekar.cocky.shared.generated.resources.recipe_action_create
import tech.dekar.cocky.shared.generated.resources.recipe_action_edit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreen(
    initialRecipe: Recipe? = null,
    onSave: (Recipe) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(initialRecipe?.title ?: "") }
    var description by remember { mutableStateOf(initialRecipe?.description ?: "") }
    var imageUrl by remember { mutableStateOf(initialRecipe?.imageUrl ?: "") }
    var videoUrl by remember { mutableStateOf(initialRecipe?.videoUrl ?: "") }

    BaseScreen(
        topBar = {
            TopAppBar(
                title = { Text(text = if (initialRecipe == null)
                    stringResource(Res.string.recipe_action_create)
                else stringResource(Res.string.recipe_action_edit)) }
            )
        },
        bottomBar = {
            BottomAppBar {
                Icons.Filled.Add
//                Icon(Icons.Filled.Add, contentDescription = "Add")
//                Icon(Icons.Filled, contentDescription = "Add")
                Text(
                    text = if (initialRecipe == null)
                        stringResource(Res.string.recipe_action_create)
                    else stringResource(Res.string.recipe_action_edit),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge
                )
            }

        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Название") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Описание") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 5
                )
                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("URL изображения") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = videoUrl,
                    onValueChange = { videoUrl = it },
                    label = { Text("URL видео") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { onCancel() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Отмена")
                    }
                    Button(
                        onClick = {
                            val now = ""
                            val recipe = Recipe(
                                id = initialRecipe?.id ?: "",
                                createdAt = initialRecipe?.createdAt ?: now,
                                updatedAt = now,
                                title = title,
                                description = description.takeIf { it.isNotBlank() },
                                imageUrl = imageUrl.takeIf { it.isNotBlank() },
                                videoUrl = videoUrl.takeIf { it.isNotBlank() }
                            )
                            onSave(recipe)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Сохранить")
                    }
                }
            }

        }
    )
}