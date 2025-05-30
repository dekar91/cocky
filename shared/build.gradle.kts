plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.main.compose)
    alias(libs.plugins.kotlin.kotest)
}


kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidTarget()

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "sharedKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
            isStatic = true
        }
    }

    jvm("desktop")

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        all {
            languageSettings {
                languageVersion = libs.versions.kotlin.compiler.get()
                apiVersion = libs.versions.kotlin.compiler.get()
            }
        }
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(libs.kotlin.compose.ui.graphics)
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines)
                implementation(libs.koin.core)
                implementation(libs.kotlin.compose.material.icons.core)
                // Add KMP dependencies here

                implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-beta01")

                // Remove androidx navigation for desktop compatibility
                // implementation("androidx.navigation:navigation-compose:2.9.0")
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlinx.jetbrains.test)
                implementation(libs.kotlinx.coroutines.test)
//                implementation(libs.mockk.common) // limited mockk for common
                implementation(libs.kotlin.kotest.engine)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
                implementation(libs.kotlin.compose.ui.tooling.preview)
                implementation(libs.sqldelight.android)
//                implementation("androidx.navigation:navigation-compose:2.7.7")
            }
        }

        androidInstrumentedTest {
            dependencies {
                // Android/instrumented tests
                implementation(libs.androidx.junit)
                implementation(libs.mockk)
                implementation(libs.kotlin.kotest.runner)

                implementation(libs.androidx.espresso.core)
                implementation(project.dependencies.platform(libs.androidx.compose.bom))
                implementation(libs.androidx.ui.test.junit4)
                // Add Android-specific test dependencies here
            }
        }

        iosMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlin.compose.runtime)
                implementation(libs.kotlin.compose.foundation)
                implementation(libs.kotlin.compose.material3)
                implementation(libs.kotlin.compose.ui)
                implementation(libs.kotlin.compose.ui.graphics)
//                implementation(libs.kotlin.compose.ui.tooling.preview)


                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
        val desktopMain by getting

            desktopMain.dependencies {
                implementation(compose.desktop.common)
                implementation(libs.sqldelight.sqlite) // SQLite (для дескт)
            }

        val desktopTest by getting

        desktopTest.dependencies {
            implementation(libs.junit.jupiter.api)
            runtimeOnly(libs.junit.jupiter.engine)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.kotlinx.jetbrains.test)
            implementation(libs.mockk)
        }

    }
}

android {
    namespace = "tech.dekar.cocky.shared"    // ваш package для R/Res классов
    compileSdkVersion(35)

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    sourceSets["main"].assets.srcDir(
//        // подключаем сгенерированные .cvr-файлы в assets
//        "$rootDir.bu/generated/composeResources/commonMain/assets"
//    )
//
//    // Если нужно — остальные настройки тестов:
//    sourceSets["androidTest"].apply {
//        // ваша папка с тестами, если нет дефолтной
//        java.srcDir("src/test/java")
//    }
}

//tasks.withType<app.cash.sqldelight.gradle.VerifyMigrationTask>().configureEach {
//    val folder = "${project.buildDir}/tmp/sqlite"
//    doFirst {
//        file(folder).mkdirs()
//        System.setProperty("org.sqlite.tmpdir", folder)
//        System.setProperty("java.io.tmpdir", folder)
//    }
//}

sqldelight {
    databases {
        create("CockyDatabase") {
            packageName.set("tech.dekar.cocky.shared.db")
            verifyMigrations.set(true)
            deriveSchemaFromMigrations.set(true)
            migrationOutputDirectory.set(file("${getLayout().buildDirectory}/tmp/migrations"))
        }
    }
}

compose.resources {
    // force generation even if this is only a transitive dependency
//    generateResClass = "always"
    // make the Res class public so Android sees it
    publicResClass = true
    // match your package structure
    packageOfResClass = "tech.dekar.cocky.shared.generated.resources"
}