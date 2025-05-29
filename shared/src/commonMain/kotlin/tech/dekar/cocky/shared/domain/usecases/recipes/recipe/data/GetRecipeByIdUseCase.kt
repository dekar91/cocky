package tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data

import tech.dekar.cocky.shared.domain.repository.RecipeLocalDataSource

class GetRecipeByIdUseCase(
    private val recipeLocalDataSource: RecipeLocalDataSource,

    ) {
    operator fun invoke(id: String)  = recipeLocalDataSource.selectById(id)
}