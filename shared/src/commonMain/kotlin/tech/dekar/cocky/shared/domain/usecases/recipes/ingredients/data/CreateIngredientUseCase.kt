package tech.dekar.cocky.shared.domain.usecases.recipes.ingredients.data

import tech.dekar.cocky.shared.domain.model.Ingredient
import tech.dekar.cocky.shared.domain.repository.IngredientLocalDataSource

class CreateIngredientUseCase(
    private val ingredientLocalDataSource: IngredientLocalDataSource

) {
    operator fun invoke(data: Ingredient) = ingredientLocalDataSource.insert(data)
}