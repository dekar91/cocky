package tech.dekar.cocky.shared.domain.usecases.recipes.recipe

import tech.dekar.cocky.shared.domain.model.Recipe
import tech.dekar.cocky.shared.domain.repository.VideoAnalyzer

class ExtractRecipeUseCase(
    private val videoAnalyzer: VideoAnalyzer
) {
    suspend fun execute(videoBytes: ByteArray): Recipe {
        return videoAnalyzer.extract(videoBytes)
    }
}
