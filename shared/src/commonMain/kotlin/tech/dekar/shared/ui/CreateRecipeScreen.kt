package tech.dekar.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import tech.dekar.shared.domain.model.Recipe
import kotlin.time.Instant

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (initialRecipe == null) "Создать рецепт" else "Редактировать рецепт") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
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
                            val now = Instant.now().toString()
                            val recipe = Recipe(
                                id = initialRecipe?.id ?: UUID.randomUUID().toString(),
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
