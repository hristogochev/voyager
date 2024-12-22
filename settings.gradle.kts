enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

//rootProject.name = "Vortex"
include(":vortex")
include(":vortex-koin")
include(":vortex-kodein")
include(":samples:android")
include(":samples:multiplatform")
//include( ":samples:android",
//    ":samples:multiplatform",
//
//    ":samples:multi-module:app",
//    ":samples:multi-module:navigation",
//    ":samples:multi-module:feature-home",
//    ":samples:multi-module:feature-posts",)

