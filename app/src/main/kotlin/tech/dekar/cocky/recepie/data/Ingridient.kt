package tech.dekar.cocky.recepie.data

data class Ingridient(
    val name: String,
    val quantity: String,
    val unit: String? = null,
    val isOptional: Boolean = false,
    val isAllergen: Boolean = false
)
