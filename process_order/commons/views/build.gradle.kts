import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

android{
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(project(BuildModules.Commons.VIEWS))

    Dependencies.apply {
        implementation(APPCOMPAT)
        implementation(ANDROID_LEGACY)
        implementation(CORE_KTX)
        implementation(MATERIAL)
        implementation(CONSTRAIN_LAYOUT)
        implementation(RECYCLE_VIEW)

        // shimmer
        implementation(SHIMMER)
    }
}