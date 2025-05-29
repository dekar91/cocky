package tech.dekar.cocky.shared

import java.util.UUID

actual fun platform() = "Android"
actual fun randomUUID(): String =
    UUID.randomUUID().toString()