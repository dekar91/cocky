package tech.dekar.shared.domain.usecases.recipes.steps.data

import tech.dekar.shared.domain.model.RecipeStep
import tech.dekar.shared.domain.repository.RecipeStepsLocalDataSource

class CreateRecipeStepUseCase(
    private val recipeStepsRepository: RecipeStepsLocalDataSource
) {
    operator fun invoke(step: RecipeStep) = recipeStepsRepository.insert(step)
}