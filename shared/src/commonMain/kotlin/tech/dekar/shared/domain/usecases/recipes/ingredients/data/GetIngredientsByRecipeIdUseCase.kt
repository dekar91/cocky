package tech.dekar.shared.domain.usecases.recipes.ingredients.data

import tech.dekar.shared.domain.repository.IngredientLocalDataSource

class GetIngredientsByRecipeIdUseCase(
    private val ingredientLocalDataSource: IngredientLocalDataSource
) {
    operator fun invoke(id: String)  = ingredientLocalDataSource.getByRecipeId(id)
}