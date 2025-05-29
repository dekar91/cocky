package tech.dekar.cocky.shared.domain.model

data class RecipeStep(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val recipeId: String,
    val stepNumber: Int,
    val description: String,
    val imageUrl: String? = null
) : DbDataModel