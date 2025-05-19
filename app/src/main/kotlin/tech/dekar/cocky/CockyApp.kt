package tech.dekar.cocky

import dagger.hilt.android.HiltAndroidApp
import io.sentry.android.core.SentryAndroid
import jakarta.inject.Inject
import tech.dekar.cocky.configs.SentryConfig
import tech.dekar.cocky.configs.Utils.logTag
import tech.dekar.cocky.di.Logger

@HiltAndroidApp
class CockyApp : BaseApp() {

    @Inject
    lateinit var sentryConfig: SentryConfig

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()

        if (sentryConfig.enabled) {
            initSentry()
        } else {
            logger.i(logTag(), "Sentry is disabled")
        }
    }

    fun initSentry() {
        println(sentryConfig)
        SentryAndroid.init(applicationContext) { options ->
            options.dsn = sentryConfig.dsn
            options.isSendDefaultPii = sentryConfig.sendDefaultPii
            options.isEnableUserInteractionTracing = sentryConfig.tracesUserInteractionEnable
            options.isEnableUserInteractionBreadcrumbs = sentryConfig.attachScreenshot // / ?
            options.viewHierarchyExporters

            options.tracesSampleRate = sentryConfig.tracesSampleRate

            // Profiling
            options.profilesSampleRate = sentryConfig.tracesProfilingSampleRate
            options.profileLifecycle = sentryConfig.tracesProfilingLifecycle
            options.isStartProfilerOnAppStart = sentryConfig.startProfilingOnStart

            // Уведомление о успешной инициализации
            println("Sentry initialized")
        }
    }
}
