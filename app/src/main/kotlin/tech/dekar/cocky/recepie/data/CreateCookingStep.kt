package tech.dekar.cocky.recepie.data

class CreateCookingStep(
    override val recipeId: String,
    override val step: String,
    override val imageUrl: String?,
    override val duration: Int?,
    override val order: Int
) : RequiredStepInfo
