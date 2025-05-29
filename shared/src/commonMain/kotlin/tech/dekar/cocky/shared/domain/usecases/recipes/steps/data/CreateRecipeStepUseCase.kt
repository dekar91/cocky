package tech.dekar.cocky.shared.domain.usecases.recipes.steps.data

import tech.dekar.cocky.shared.domain.model.RecipeStep
import tech.dekar.cocky.shared.domain.repository.RecipeStepsLocalDataSource

class CreateRecipeStepUseCase(
    private val recipeStepsRepository: RecipeStepsLocalDataSource
) {
    operator fun invoke(step: RecipeStep) = recipeStepsRepository.insert(step)
}