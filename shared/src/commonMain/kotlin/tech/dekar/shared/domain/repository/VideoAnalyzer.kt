package tech.dekar.shared.domain.repository

import tech.dekar.shared.domain.model.Recipe

interface VideoAnalyzer {
    suspend fun extract(video: ByteArray): Recipe
}
