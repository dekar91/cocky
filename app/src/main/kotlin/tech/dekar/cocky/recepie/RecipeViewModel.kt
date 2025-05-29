package tech.dekar.cocky.recepie

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel(
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _ingredients = MutableStateFlow(listOf<Pair<String, String>>())
    val ingredients: StateFlow<List<Pair<String, String>>> = _ingredients

    private val _steps = MutableStateFlow(listOf<String>())
    val steps: StateFlow<List<String>> = _steps

    private val _imageUrl = MutableStateFlow("")
    val imageUrl: StateFlow<String> = _imageUrl

    private val _youtubeUrl = MutableStateFlow("")
    val youtubeUrl: StateFlow<String> = _youtubeUrl

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun addIngredient() {
        _ingredients.update { it + ("" to "") }
    }

    fun updateIngredient(index: Int, name: String, amount: String) {
        _ingredients.update { current ->
            current.mapIndexed { i, ingredient ->
                if (i == index) name to amount else ingredient
            }
        }
    }

    fun removeIngredient(index: Int) {
        _ingredients.update { it.filterIndexed { i, _ -> i != index } }
    }

    fun addStep() {
        _steps.update { it + "" }
    }

    fun updateStep(index: Int, newStep: String) {
        _steps.update { current ->
            current.mapIndexed { i, step ->
                if (i == index) newStep else step
            }
        }
    }

    fun removeStep(index: Int) {
        _steps.update { it.filterIndexed { i, _ -> i != index } }
    }

    fun updateImageUrl(newUrl: String) {
        _imageUrl.value = newUrl
    }

    fun updateYoutubeUrl(newUrl: String) {
        _youtubeUrl.value = newUrl
    }

    fun saveRecipe() {
        val recipe = Recipe(
            title = _title.value,
            ingredients = _ingredients.value,
            steps = _steps.value,
            imageUrl = _imageUrl.value,
            youtubeUrl = _youtubeUrl.value
        )
        println("Recipe saved: $recipe")
    }
}

data class Recipe(
    val title: String,
    val ingredients: List<Pair<String, String>>,
    val steps: List<String>,
    val imageUrl: String,
    val youtubeUrl: String
)
