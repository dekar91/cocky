package tech.dekar.shared.domain.usecases.recipes.recipe

import tech.dekar.shared.domain.model.Recipe
import tech.dekar.shared.domain.repository.VideoAnalyzer

class ExtractRecipeUseCase(
    private val videoAnalyzer: VideoAnalyzer
) {
    suspend fun execute(videoBytes: ByteArray): Recipe {
        return videoAnalyzer.extract(videoBytes)
    }
}
