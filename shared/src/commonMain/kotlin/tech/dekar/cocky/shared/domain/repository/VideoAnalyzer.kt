package tech.dekar.cocky.shared.domain.repository

import tech.dekar.cocky.shared.domain.model.Recipe

interface VideoAnalyzer {
    suspend fun extract(video: ByteArray): Recipe
}
