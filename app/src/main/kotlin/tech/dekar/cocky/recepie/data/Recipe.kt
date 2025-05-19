package tech.dekar.cocky.recepie.data

import java.util.UUID

data class Recipe(
    val id: UUID,
    override val name: String,
    override val description: String,
    override val imageUrl: String,
    override val videoUrl: String,
    override val ingredients: List<Ingridient>,
    override val steps: List<CookingStep>
) : RequiredRecipeInfo
