package tech.dekar.cocky.recepie.data

interface RequiredRecipeInfo {
    val name: String
    val description: String?
    val imageUrl: String?
    val videoUrl: String?
    val ingredients: List<Ingridient>
    val steps: List<RequiredStepInfo>
}
