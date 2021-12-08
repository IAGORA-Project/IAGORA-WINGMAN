import dependencies.Dependencies
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

android {
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    Dependencies.apply {
        // common requirement
        implementation(APPCOMPAT)

        // common ui
        implementation(MATERIAL)
        implementation(NAVIGATION_UI)
        implementation(NAVIGATION_FRAGMENT)
        implementation(CONSTRAIN_LAYOUT)
        implementation(GLIDE)
    }
}
