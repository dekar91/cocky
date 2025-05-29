package tech.dekar.shared

actual fun platform() = "Android"
actual fun randomUUID(): String =
    java.util.UUID.randomUUID().toString()