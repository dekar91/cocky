package tech.dekar.shared.platform.vision

expect class NativeAnalyzer {
    suspend fun process(video: ByteArray): String
}
