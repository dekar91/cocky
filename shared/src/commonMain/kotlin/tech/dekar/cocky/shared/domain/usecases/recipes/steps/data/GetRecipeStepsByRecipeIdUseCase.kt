package tech.dekar.cocky.shared.domain.usecases.recipes.steps.data

import tech.dekar.cocky.shared.domain.repository.RecipeStepsLocalDataSource

class GetRecipeStepsByRecipeIdUseCase(
    private val recipeStepsRepository: RecipeStepsLocalDataSource
) {
    operator fun invoke(recipeId: String) = recipeStepsRepository.getByRecipeId(recipeId)
}