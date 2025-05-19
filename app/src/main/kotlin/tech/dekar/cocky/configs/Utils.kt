package tech.dekar.cocky.configs

import kotlin.jvm.java

object Utils {
    inline fun <reified T> T.logTag(): String = T::class.java.simpleName
}
