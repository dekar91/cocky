package tech.dekar.shared.domain.repository

import tech.dekar.shared.db.CockyDatabase
import tech.dekar.shared.db.Ingredients
import tech.dekar.shared.domain.model.Ingredient

class IngredientLocalDataSource (db: CockyDatabase):Repository<Ingredients, Ingredient>() {
    private val queries = db.ingredientsQueries

   fun getByRecipeId(recipeId: String): List<Ingredient> =
        queries.selectByRecipeId(recipeId)
            .executeAsList()
            .map { toModel(it) }

    override fun insert(item: Ingredient): Result<Long> = runCatching {
        queries.insert(
            id = item.id,
            created_at = item.createdAt,
            updated_at = item.updatedAt,
            name = item.name,
            quantity = item.quantity.toString(),
            unit = item.unit,
            recipe_id = item.recipeId
        ).value
    }


    override fun toModel(queryResult: Ingredients): Ingredient =
        Ingredient(
            id = queryResult.id,
            createdAt = queryResult.created_at,
            updatedAt = queryResult.updated_at,
            recipeId = queryResult.recipe_id,
            name = queryResult.name,
            quantity = queryResult.quantity,
            unit = queryResult.unit,
        )
}