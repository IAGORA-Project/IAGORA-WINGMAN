import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

dependencies{
    implementation(project(BuildModules.HELPER))

    // remote
    implementation(Dependencies.RETROFIT_GSON)
}
