package tech.dekar.cocky.shared.domain.usecases.recipes.recipe

import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.CreateRecipeUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.GetAllRecipesUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.recipe.data.GetRecipeByIdUseCase

data class RecipeUseCases(
    val getAll: GetAllRecipesUseCase,
    val getById: GetRecipeByIdUseCase,
    val create: CreateRecipeUseCase
)