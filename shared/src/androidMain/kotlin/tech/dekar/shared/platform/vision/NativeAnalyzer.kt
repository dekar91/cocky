package tech.dekar.shared.platform.vision

actual class NativeAnalyzer {
    actual suspend fun process(video: ByteArray): String {
        return  ""
        // MLKit или OpenCV код
    }
}
