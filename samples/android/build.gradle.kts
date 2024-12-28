plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    id("kotlin-parcelize")
}


android {
    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "io.github.hristogochev.vortex.sample"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.github.hristogochev.vortex.sample"
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":vortex"))
    implementation(project(":vortex-koin"))
    implementation(project(":vortex-kodein"))

    implementation(libs.kodein)
    implementation(libs.koin.compose)
    implementation(libs.koin)

    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.activity)
}
