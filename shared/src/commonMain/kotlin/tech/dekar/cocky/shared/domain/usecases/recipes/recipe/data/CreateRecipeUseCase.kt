package tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data

import tech.dekar.cocky.shared.domain.model.Recipe
import tech.dekar.cocky.shared.domain.repository.RecipeLocalDataSource

class CreateRecipeUseCase(
    private val recipeLocalDataSource: RecipeLocalDataSource

) {
    suspend operator fun invoke(recipe: Recipe) = recipeLocalDataSource.insert(recipe)
}