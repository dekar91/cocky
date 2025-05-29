package tech.dekar.shared

actual fun platform() = "Desktop"
actual fun randomUUID(): String =
    java.util.UUID.randomUUID().toString()