package tech.dekar.cocky.shared.domain.model

data class Ingredient(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val recipeId: String,
    val name: String,
    // String, because it can be a number or a fraction
    val quantity: String? = null,
    val unit: RecipeUnit? = null,
): DbDataModel
