package tech.dekar.cocky.shared.domain.usecases.recipes.ingredients

import tech.dekar.cocky.shared.domain.usecases.recipes.ingredients.data.CreateIngredientUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.GetRecipeByIdUseCase

data class IngredientUseCases(
    val getRecipeById: GetRecipeByIdUseCase,
    val create: CreateIngredientUseCase
)