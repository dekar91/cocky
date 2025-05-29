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


    override fun toModel(data: Ingredients): Ingredient =
        Ingredient(
            id = data.id,
            createdAt = data.created_at,
            updatedAt = data.updated_at,
            recipeId = data.recipe_id,
            name = data.name,
            quantity = data.quantity,
            unit = data.unit,
        )
}