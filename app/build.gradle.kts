import me.moallemi.gradle.advancedbuildversion.gradleextensions.VersionCodeType
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    alias(libs.plugins.hilt) // Hilt DI
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ktlint.idea)
    alias(libs.plugins.sentry)
    kotlin("kapt") // Hilt compiler
    alias(libs.plugins.advancedBuildVersion)
}

val googleServicesFile = rootProject.file("app/google-services.json")
if (googleServicesFile.exists()) {
    apply(plugin = libs.plugins.firebase.get().pluginId)
    apply(plugin = libs.plugins.crashlytics.get().pluginId)
}

advancedVersioning {
//    outputOptions {
//        renameOutput(true)
//    }
    codeOptions {
        versionCodeType(VersionCodeType.GIT_COMMIT_COUNT)
    }
}

fun buildConfigString(value: String): String {
    return "\"$value\""
}

android {
    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
        }
    }

    namespace = "tech.dekar.cocky"
    compileSdk = 35

    fun getLocalProperty(key: String, defaultValue: String = ""): String {
        val localProperties = Properties()
        val file = file("${rootProject.rootDir}/local.properties")
        if (file.exists()) {
            file.inputStream().use { localProperties.load(it) }
        }
        return localProperties.getProperty(key, defaultValue)
    }

    fun String.execute(): String {
        return try {
            ProcessBuilder(*this.split(" ").toTypedArray())
                .redirectErrorStream(true)
                .start()
                .inputStream
                .bufferedReader()
                .use { it.readText().trim() }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun getGitVersionName(): String {
        // Получаем последний тег (например, v1.2.3)
        val tag = "git describe --tags --abbrev=0".execute().trim()
//
//        // Получаем количество коммитов с последнего тега (например, 5)
        val commitsSinceTag = "git rev-list $tag..HEAD --count".execute().trim()
//
//        // Получаем короткий хеш последнего коммита (например, a1b2c3d)
        val commitHash = "git rev-parse --short HEAD".execute().trim()
//
        println(tag)
//
//        // Парсим тег, чтобы разделить номер версии и канал (например, v1.2.3-beta1)
        val versionParts = tag.removePrefix("v").split("-")
        val version = versionParts[0] // 1.2.3
//
//        // Канал (если есть), например "beta1" или "alpha3"
        val channel = if (versionParts.size > 1) versionParts[1] else ""
//
//        // Формируем имя версии
        val versionName = if (commitsSinceTag == "0") {
//            // Если коммитов с последнего тега нет, просто возвращаем тег
            version + if (channel.isNotEmpty()) "-$channel" else ""
        } else {
//            // Если коммиты были, добавляем их количество и хеш
            "$version-$channel+$commitsSinceTag-$commitHash"
        }
//
        println("Version name: $versionName")

        return versionName
    }

// Используем значение из local.properties если доступно
    val sentryDsn = getLocalProperty(
        "SENTRY_DSN",
        project.findProperty("SENTRY_DSN") as String? ?: buildConfigString("")
    )

    defaultConfig {
        applicationId = "tech.dekar.cocky"
        minSdk = 21
        targetSdk = 35
        versionCode = 11 // advancedVersioning.versionCode
        versionName = getGitVersionName()

        testInstrumentationRunner = "tech.dekar.lockme.CustomTestRunner"

        buildConfigField("Boolean", "SENTRY_ENABLED", "false")
        buildConfigField("String", "SENTRY_DSN", sentryDsn)
        buildConfigField("Boolean", "SENTRY_SEND_DEFAULT_PII", "false")
        buildConfigField("Boolean", "SENTRY_TRACES_USER_INTERACTION_ENABLE", "false")
        buildConfigField("Boolean", "SENTRY_ATTACH_SCREENSHOT", "false")
        buildConfigField("Boolean", "SENTRY_ATTACH_VIEW_HIERARCHY", "false")
        buildConfigField("Double", "SENTRY_TRACES_SAMPLE_RATE", "1.0")

        buildConfigField("Double", "SENTRY_TRACES_PROFILING_SAMPLE_RATE", "1.0")
        buildConfigField("String", "SENTRY_TRACES_PROFILING_LIFECYCLE", buildConfigString("trace"))
        buildConfigField("Boolean", "SENTRY_START_PROFILING_ON_START", "false")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            manifestPlaceholders.apply {
                put("firebase_crashlytics_collection_enabled", "true")
                put("firebase_analytics_collection_enabled", "true")
            }
            buildConfigField("Boolean", "SENTRY_ENABLED", "true")
            buildConfigField("Boolean", "SENTRY_TRACES_USER_INTERACTION_ENABLE", "true")
        }

        debug {
            // полностью отключаем сбор аналитики и краш-репортов
            manifestPlaceholders.apply {
                put("firebase_crashlytics_collection_enabled", "false")
                put("firebase_analytics_collection_enabled", "false")
            }

            buildConfigField("Boolean", "SENTRY_ENABLED", "false")
            buildConfigField("Boolean", "SENTRY_TRACES_USER_INTERACTION_ENABLE", "true")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true

        sourceSets {
            getByName("androidTest") {
                assets.srcDirs("src/androidTest/assets")
                kotlin.srcDirs("src/androidTest/kotlin")
                res.srcDirs("src/androidTest/res")
            }
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        implementation(project(":shared"))
        // Firebase
        implementation(platform(libs.firebase.bom))
        implementation(libs.firebase.analytics)
        implementation(libs.firebase.crashlytics)

        // 🟢 Dependency Injection (Hilt)
        implementation(libs.hilt.android)

        kapt(libs.hilt.compiler)
        implementation(libs.androidx.hilt.navigation.compose)

        // 🧱 Core & Lifecycle
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.lifecycle.runtime.compose)
        implementation(libs.androidx.lifecycle.viewmodel.compose)

        implementation(libs.androidx.appcompat)
        implementation(libs.android.material3)

        // 🧩 Compose
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.material3.icons.extended)

        // 💾 DataStore
        implementation(libs.androidx.datastore.preferences)
        implementation(libs.androidx.datastore.preferences.core)

        // 🎵 Media
        implementation(libs.androidx.media3.exoplayer)
        implementation(libs.androidx.media3.exoplayer.dash)
        implementation(libs.generativeai)

        // Unit tests
        testImplementation(libs.junit.jupiter.api)
        testRuntimeOnly(libs.junit.jupiter.engine)
        testImplementation(libs.androidx.junit)
        testImplementation(libs.kotlinx.coroutines.test)
        testImplementation(libs.kotlinx.jetbrains.test)
        testImplementation(libs.mockk)

        // Android/instrumented tests
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        androidTestImplementation(libs.hilt.testing)
        androidTestImplementation(libs.androidx.navigation.testing)
        kaptAndroidTest(libs.hilt.compiler)
        androidTestImplementation(libs.mockk.android)
        androidTestImplementation(libs.skyscreamer.jsonassert)

        // 🐞 Debug
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }

    tasks.withType<KtLintFormatTask>().configureEach {
        setSource(
            fileTree("src") {
                include("**/*.kt")
            }
        )
    }
}
