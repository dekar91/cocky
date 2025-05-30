package tech.dekar.cocky.shared.domain.usecases.recipes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import tech.dekar.cocky.shared.domain.model.Recipe
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.CreateRecipeUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.GetAllRecipesUseCase
import tech.dekar.cocky.shared.randomUUID
import tech.dekar.cocky.shared.ui.control.NavigationBus
import tech.dekar.cocky.shared.ui.control.Screens

class RecipesViewModel(
    private val navigationBus: NavigationBus,
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val createRecipeUseCase: CreateRecipeUseCase
) : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _recipes.value = getAllRecipesUseCase(null, 50)
        }
    }

    fun createRecipe(title: String, description: String, imageUrl: String) {
        viewModelScope.launch {
            val recipe = Recipe(
                title = title,
                description = description,
                imageUrl = imageUrl,
                id = randomUUID().toString(),
                createdAt = "",
                updatedAt = "",
                videoUrl = null
            )
            createRecipeUseCase(recipe)
            _recipes.value = _recipes.value + recipe
            navigationBus.emit(Screens.RecipesList)
        }
    }

    fun deleteRecipe(id: Long) {
        viewModelScope.launch {
            // TODO: Delete from database
//            _recipes.value = _recipes.value.filter { it.id != id }
        }
    }

    fun navigateToCreateRecipe() {
        runBlocking { navigationBus.emit(Screens.CreateRecipe) }
    }
}
