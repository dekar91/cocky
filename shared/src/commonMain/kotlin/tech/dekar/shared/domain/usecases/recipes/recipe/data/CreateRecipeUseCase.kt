package tech.dekar.shared.domain.usecases.recipes.recipe.data

import tech.dekar.shared.domain.model.Recipe
import tech.dekar.shared.domain.repository.RecipeLocalDataSource

class CreateRecipeUseCase(
    private val recipeLocalDataSource: RecipeLocalDataSource

) {
    suspend operator fun invoke(recipe: Recipe) = recipeLocalDataSource.insert(recipe)
}