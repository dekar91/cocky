
import dev.icerock.gradle.MRVisibility

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.sqldelight)
    id("dev.icerock.mobile.multiplatform-resources")
}


kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "tech.dekar.shared"
        compileSdk = 35
        minSdk = 21

//        withHostTestBuilder {
//        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "sharedKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
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
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlin.compose.runtime)
                implementation(libs.kotlin.compose.foundation)
                implementation(libs.kotlin.compose.material3)
                implementation(libs.kotlin.compose.ui)
                implementation(libs.kotlin.compose.ui.graphics)
                implementation(libs.kotlin.compose.ui.tooling.preview)

                implementation("dev.icerock.moko:resources:0.24.5")
                implementation("dev.icerock.moko:resources-compose:0.24.5") // for compose multiplatform



                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines)

                implementation(libs.koin.core)

                // Add KMP dependencies here
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlinx.jetbrains.test)

                implementation("dev.icerock.moko:resources-test:0.24.5")

            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.

                implementation(libs.sqldelight.android)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
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
        val desktopMain by getting {
            dependencies {

                implementation(libs.sqldelight.sqlite) // SQLite (для дескт)

            }
        }
    }
}

//tasks.withType<VerifyMigrationTask>().configureEach {
//    val folder = "${rootProject.layout.buildDirectory}/tmp/sqlite"
//    doFirst {
//        file(folder).mkdirs()
//    }
//    jvm
//
////    jvmArgs("-Dorg.sqlite.tmpdir=${rootProject.layout.buildDirectory}/tmp/sqlite")
//}

sqldelight {
    databases {
        create("CockyDatabase") {
            packageName.set("tech.dekar.shared.db")
        }
    }
}

multiplatformResources {
    resourcesPackage.set("tech.dekar.shared") // required
    resourcesClassName.set("SharedRes") // optional, default MR
    resourcesVisibility.set(MRVisibility.Internal) // optional, default Public
}