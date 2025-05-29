package tech.dekar.shared.domain.usecases.recipes.steps.data

import tech.dekar.shared.domain.repository.RecipeStepsLocalDataSource

class GetRecipeStepsByRecipeIdUseCase(
    private val recipeStepsRepository: RecipeStepsLocalDataSource
) {
    operator fun invoke(recipeId: String) = recipeStepsRepository.getByRecipeId(recipeId)
}