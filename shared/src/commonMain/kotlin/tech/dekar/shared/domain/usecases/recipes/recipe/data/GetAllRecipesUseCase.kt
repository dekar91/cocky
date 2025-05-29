package tech.dekar.shared.domain.usecases.recipes.recipe.data

import tech.dekar.shared.domain.repository.RecipeLocalDataSource

class GetAllRecipesUseCase(
    private val recipeLocalDataSource: RecipeLocalDataSource

) {
    operator fun invoke(cursor: String?, limit: Long) = if(cursor.isNullOrBlank()) {
        recipeLocalDataSource.getAll(limit)
    } else {
        recipeLocalDataSource.getAll(cursor, limit)
    }
}