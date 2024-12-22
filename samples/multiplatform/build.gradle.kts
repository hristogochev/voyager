import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi
import org.jetbrains.compose.desktop.application.tasks.AbstractNativeMacApplicationPackageTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.util.Locale

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
}


android {
//    kotlinOptions {
//        jvmTarget = "11"
//    }
    namespace = "com.hristogochev.vortex.sample.multiplatform"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.hristogochev.vortex.sample.multiplatform"
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}




kotlin {

    // Android
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // Desktop
    jvm("desktop")

    // iOS
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    // Wasm
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    // JS
    js(IR) {
        browser()
        binaries.executable()
    }

    // Native Macos experimental
    val macOsConfiguration: KotlinNativeTarget.() -> Unit = {
        binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                )
            }
        }
    }
    macosX64(macOsConfiguration)
    macosArm64(macOsConfiguration)

    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.runtime)

            implementation(project(":vortex"))
        }

        androidMain.dependencies {
            implementation(libs.compose.activity)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    defaultConfig {
        applicationId = "com.hristogochev.vortex.sample.multiplatform"
    }
}

compose.desktop {
    application {
        mainClass = "com.hristogochev.vortex.sample.multiplatform.AppKt"
        nativeDistributions {
            targetFormats(Dmg, Msi, Deb)
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}


// Native Macos experimental
compose.desktop.nativeApplication {
    targets(kotlin.targets.getByName("macosX64"), kotlin.targets.getByName("macosArm64"))
    distributions {
        targetFormats(Dmg)
        packageName = "MultiplatformSample"
        packageVersion = "1.0.0"
    }
}


afterEvaluate {
    val baseTask = "createDistributableNative"
    val architectures = listOf("macosX64", "macosArm64")
    val buildTypes = listOf("debug", "release")

    architectures.forEach { architecture ->
        buildTypes.forEach buildTypeForEach@{ buildType ->
            val createAppTaskName = baseTask + buildType.capitalize() + architecture.capitalize()

            val createAppTask =
                tasks.findByName(createAppTaskName) as? AbstractNativeMacApplicationPackageTask?
                    ?: return@buildTypeForEach

            val destinationDir = createAppTask.destinationDir.get().asFile
            val packageName = createAppTask.packageName.get()

            tasks.create("runNative${architecture.capitalize()}${buildType.capitalize()}") {
                group = createAppTask.group
                dependsOn(createAppTaskName)
                doLast {
                    ProcessBuilder(
                        "open",
                        destinationDir.absolutePath + "/" + packageName + ".app"
                    ).start().waitFor()
                }
            }
        }
    }
}

private fun String.capitalize(): String {
    return replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

