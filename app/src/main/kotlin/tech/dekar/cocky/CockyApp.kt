package tech.dekar.cocky

import io.sentry.android.core.SentryAndroid
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import tech.dekar.cocky.configs.SentryConfig
import tech.dekar.cocky.configs.Utils.logTag
import tech.dekar.cocky.di.KoinAndroidModule.androidModule
import tech.dekar.cocky.shared.di.CommonMainKoinModule.commonMainModule
import tech.dekar.cocky.shared.di.Logger

class CockyApp : BaseApp() {

    val sentryConfig: SentryConfig by inject<SentryConfig>()

    val logger: Logger by inject<Logger>()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CockyApp)
            modules(commonMainModule, androidModule)
        }

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
