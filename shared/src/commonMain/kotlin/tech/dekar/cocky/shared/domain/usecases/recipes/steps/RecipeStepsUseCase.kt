package tech.dekar.cocky.shared.domain.usecases.recipes.steps

import tech.dekar.cocky.shared.domain.usecases.recipes.steps.data.CreateRecipeStepUseCase
import tech.dekar.cocky.shared.domain.usecases.recipes.steps.data.GetRecipeStepsByRecipeIdUseCase

data class RecipeStepsUseCase(
    val getByRecipeId: GetRecipeStepsByRecipeIdUseCase,
    val create: CreateRecipeStepUseCase,
)
