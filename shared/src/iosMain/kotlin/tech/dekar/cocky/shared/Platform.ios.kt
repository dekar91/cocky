package tech.dekar.cocky.shared

import platform.Foundation.NSUUID

actual fun platform() = "iOS"
actual fun randomUUID(): String =
    NSUUID().UUIDString()
