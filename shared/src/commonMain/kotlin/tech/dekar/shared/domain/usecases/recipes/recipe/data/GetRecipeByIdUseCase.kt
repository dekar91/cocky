package tech.dekar.shared.domain.usecases.recipes.recipe.data

import tech.dekar.shared.domain.repository.RecipeLocalDataSource

class GetRecipeByIdUseCase(
    private val recipeLocalDataSource: RecipeLocalDataSource,

    ) {
    operator fun invoke(id: String)  = recipeLocalDataSource.selectById(id)
}