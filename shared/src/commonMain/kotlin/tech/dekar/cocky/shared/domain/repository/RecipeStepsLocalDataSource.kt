package tech.dekar.cocky.shared.domain.repository

import tech.dekar.cocky.shared.db.CockyDatabase
import tech.dekar.cocky.shared.domain.model.RecipeStep
import tech.dekar.shared.db.Recipe_steps

class RecipeStepsLocalDataSource(db: CockyDatabase): Repository<Recipe_steps, RecipeStep>() {
    private val queries = db.recipeStepsQueries


    override  fun insert(item: RecipeStep): Result<Long> = runCatching {
        queries.insert(
            id = item.id,
            created_at = item.createdAt,
            updated_at = item.updatedAt,
            recipe_id = item.recipeId,
            step_number = item.stepNumber.toLong(),
            description = item.description,
            imageUrl = item.imageUrl
        ).value
    }

fun getByRecipeId(recipeId: String): List<RecipeStep> =
        queries.selectByRecipeId(recipeId)
            .executeAsList()
            .map(::toModel)



    override fun toModel(queryResult: Recipe_steps): RecipeStep =
        RecipeStep(
            id = queryResult.id,
            createdAt = queryResult.created_at,
            updatedAt = queryResult.updated_at,
            recipeId = queryResult.recipe_id,
            stepNumber = queryResult.step_number.toInt(),
            description = queryResult.description,
            imageUrl = queryResult.imageUrl
        )
}