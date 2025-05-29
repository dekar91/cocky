package tech.dekar.shared.domain.usecases.recipes.ingredients.data

import tech.dekar.shared.domain.model.Ingredient
import tech.dekar.shared.domain.repository.IngredientLocalDataSource

class CreateIngredientUseCase(
    private val ingredientLocalDataSource: IngredientLocalDataSource

) {
    operator fun invoke(data: Ingredient) = ingredientLocalDataSource.insert(data)
}