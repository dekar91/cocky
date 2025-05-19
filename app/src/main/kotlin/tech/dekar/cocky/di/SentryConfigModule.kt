package tech.dekar.cocky.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.dekar.cocky.configs.SentryConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SentryConfigModule {

    @Provides
    @Singleton
    fun provideSentryConfig(): SentryConfig {
        return SentryConfig()
    }
}
