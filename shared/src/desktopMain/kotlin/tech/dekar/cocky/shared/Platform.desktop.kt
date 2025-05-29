package tech.dekar.cocky.shared

import java.util.UUID

actual fun platform() = "Desktop"
actual fun randomUUID(): String =
    UUID.randomUUID().toString()