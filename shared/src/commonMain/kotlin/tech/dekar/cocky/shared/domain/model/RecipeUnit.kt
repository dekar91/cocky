package tech.dekar.cocky.shared.domain.model

enum class RecipeUnit {
    GRAMS,
    KILOGRAMS,
    MILLILITERS,
    LITERS,
    CUPS,
    TABLESPOONS,
    TEASPOONS,
    PIECES,
    SLICES,
    DASHES,
    PINCHES;

    companion object {
        fun fromString(value: String): RecipeUnit? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}