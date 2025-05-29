package tech.dekar.shared.domain.model

data class RecipeStep(
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val recipeId: String,
    val stepNumber: Int,
    val description: String,
    val imageUrl: String? = null
) : DbDataModel