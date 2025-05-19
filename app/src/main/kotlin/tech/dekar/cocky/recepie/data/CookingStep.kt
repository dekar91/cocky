package tech.dekar.cocky.recepie.data

data class CookingStep(
    val id: String,
    override val recipeId: String,
    override val step: String,
    override val imageUrl: String?,
    override val duration: Int?,
    override val order: Int
) : RequiredStepInfo
