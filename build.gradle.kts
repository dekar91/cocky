import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
//    alias(libs.plugins.kotlin.main.compose) apply false
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false

    alias(libs.plugins.sentry) apply false

    alias(libs.plugins.firebase) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.advancedBuildVersion) apply false
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        // Сохраняем прежние флаги и добавляем новый
        freeCompilerArgs += "-Xexpect-actual-classes"
    }
}

buildscript {
    dependencies {
        classpath( "dev.icerock.moko:resources-generator:0.24.5")
    }
}
