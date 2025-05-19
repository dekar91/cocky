package tech.dekar.cocky.recepie.data

data class CreateRecipe(
    override val name: String,
    override val description: String,
    override val imageUrl: String,
    override val videoUrl: String,
    override val ingredients: List<Ingridient>,
    override val steps: List<CreateCookingStep>

) : RequiredRecipeInfo
