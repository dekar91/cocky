package tech.dekar.cocky.di

import tech.dekar.cocky.configs.SentryConfig

object SentryConfigModule {

    fun provideSentryConfig(): SentryConfig {
        return SentryConfig()
    }
}
