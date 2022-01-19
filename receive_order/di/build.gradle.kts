import dependencies.Dependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


dependencies {
    implementation(project(BuildModules.ReceiveOrder.DATA))
    implementation(project(BuildModules.ReceiveOrder.DOMAIN))
    implementation(project(BuildModules.ReceiveOrder.PRESENTATION))

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