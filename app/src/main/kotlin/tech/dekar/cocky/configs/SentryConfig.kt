package tech.dekar.cocky.configs

import io.sentry.ProfileLifecycle
import tech.dekar.cocky.BuildConfig

data class SentryConfig(
    val enabled: Boolean = BuildConfig.SENTRY_ENABLED,
    val dsn: String = BuildConfig.SENTRY_DSN,
    val sendDefaultPii: Boolean = BuildConfig.SENTRY_SEND_DEFAULT_PII,
    val tracesUserInteractionEnable: Boolean = BuildConfig.SENTRY_TRACES_USER_INTERACTION_ENABLE,
    val attachScreenshot: Boolean = BuildConfig.SENTRY_ATTACH_SCREENSHOT,
    val tracesSampleRate: Double = BuildConfig.SENTRY_TRACES_SAMPLE_RATE,
    val tracesProfilingSampleRate: Double = BuildConfig.SENTRY_TRACES_PROFILING_SAMPLE_RATE,
    val tracesProfilingLifecycle: ProfileLifecycle = ProfileLifecycle.valueOf(BuildConfig.SENTRY_TRACES_PROFILING_LIFECYCLE.uppercase()),
    val startProfilingOnStart: Boolean = BuildConfig.SENTRY_START_PROFILING_ON_START
)
