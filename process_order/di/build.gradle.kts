import dependencies.Dependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


dependencies {
    implementation(project(BuildModules.Market.DATA))
    implementation(project(BuildModules.Market.DOMAIN))
    implementation(project(BuildModules.Market.PRESENTATION))

    Dependencies.apply {
        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // lifecycle
        implementation(LIFECYCLE_VIEWMODEL)

        // common ui
        implementation(RECYCLE_VIEW)

        // remote
        implementation(RETROFIT_GSON)
    }
}