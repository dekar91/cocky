package tech.dekar.shared.domain.repository

import tech.dekar.shared.db.CockyDatabase
import tech.dekar.shared.db.Recipes
import tech.dekar.shared.domain.model.Recipe

class RecipeLocalDataSource(db: CockyDatabase) : Repository<Recipes, Recipe>() {
    private val queries = db.recipesQueries

     fun getAll(limit: Long): List<Recipe> = queries
        .selectWithLimit(limit)
        .executeAsList()
        .map(::toModel)


     fun getAll(cursor: String, limit: Long): List<Recipe> = queries
        .selectWithCursor(cursor,limit)
        .executeAsList()
        .map(::toModel)

     fun selectById(id: String): Recipe = queries.selectById(id)
        .executeAsOne()
        .let { toModel(it) }

    override fun insert(item: Recipe): Result<Long> = runCatching {
        queries.insert(
            id = item.id,
            created_at = item.createdAt,
            updated_at = item.updatedAt,
            title = item.title,
            description = item.description,
            imageUrl = item.imageUrl,
            videoUrl = item.videoUrl
        ).value
    }


  override fun toModel(queryResult: Recipes): Recipe =
        Recipe(
            id = queryResult.id,
            createdAt = queryResult.created_at,
            updatedAt = queryResult.updated_at,
            title = queryResult.title,
            description = queryResult.description,
            imageUrl = queryResult.imageUrl,
            videoUrl = queryResult.videoUrl
        )

}
