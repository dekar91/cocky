import org.jetbrains.compose.desktop.application.dsl.TargetFormat

//import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.main.compose)
}

group = "org.example"
version = "1.0-SNAPSHOT"

//repositories {
//    mavenCentral()
//    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//    google()
//}

dependencies {
    implementation(project(":shared"))
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
//    implementation("org.jetbrains.compose.runtime:runtime")
}

compose.desktop {
    application {
        mainClass = "tech.dekar.cocky.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "tech.dekar.cocky.desktop"
            packageVersion = "1.0.0"

            windows {
                // Можно указать iconFile, если есть .ico
                // iconFile.set(project.file("src/desktopMain/resources/icon.ico"))
                menuGroup = "Cocky App"
                upgradeUuid = "dd203f26-c990-4a4d-b612-75aa916bb705" // должен быть уникальным для приложения
            }
        }
    }


}

