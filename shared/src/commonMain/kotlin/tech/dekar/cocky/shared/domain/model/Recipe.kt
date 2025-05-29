package tech.dekar.cocky.shared.domain.model


data class Recipe(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val videoUrl: String?
): DbDataModel
