package tech.dekar.cocky.recepie.data

interface RequiredStepInfo {
    val recipeId: String
    val step: String
    val imageUrl: String?
    val duration: Int?
    val order: Int
}
