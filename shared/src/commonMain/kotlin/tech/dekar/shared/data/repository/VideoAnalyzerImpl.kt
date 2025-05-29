package tech.dekar.shared.data.repository

import tech.dekar.shared.domain.model.Recipe
import tech.dekar.shared.domain.repository.VideoAnalyzer
import tech.dekar.shared.platform.vision.NativeAnalyzer

//class VideoAnalyzerImpl(
//    private val nativeAnalyzer: NativeAnalyzer
//) : VideoAnalyzer {
//    override suspend fun extract(video: ByteArray): Recipe {
//        val rawData = nativeAnalyzer.process(video)
//
//        return Recipe(
//            id = "0",
//            imageUrl = null,
//            steps = listOf("Step 1", "Step 2"),
//            ingredients = listOf("Ingredient 1", "Ingredient 2"),
//            title = "Recipe Title",
//            description = "Recipe Description",
////            description = "Recipe Description",
////            instructions = listOf("Step 1", "Step 2")
//        )
////        return mapRawDataToRecipe(rawData)
//    }
//}
