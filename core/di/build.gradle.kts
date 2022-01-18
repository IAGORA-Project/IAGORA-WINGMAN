import dependencies.Dependencies
import extensions.buildConfigStringField
import extensions.getLocalProperty
import extensions.implementation

plugins {
    id(BuildPlugins.ANDROID_LIBRARY_COSTUME)
}


android {
    buildTypes.forEach {
        try {
            it.apply {
                buildConfigStringField("BASE_URL", getLocalProperty("BASE_URL"))
                buildConfigStringField("KEY_CRYPTO_RESPONSE",
                    getLocalProperty("KEY_CRYPTO_RESPONSE"))
                buildConfigStringField("KEY_CRYPTO_AUTH", getLocalProperty("KEY_CRYPTO_AUTH"))
                buildConfigStringField("KEY_CRYPTO_DT", getLocalProperty("KEY_CRYPTO_DT"))
                buildConfigStringField("KEY_SHARED_PREFERENCE_WINGMAN_INFO",

                    getLocalProperty("KEY_SHARED_PREFERENCE_WINGMAN_INFO"))
                buildConfigStringField("KEY_SHARED_PREFERENCE_AUTH",

                    getLocalProperty("KEY_SHARED_PREFERENCE_AUTH"))
                buildConfigStringField("KEY_SHARED_PREFERENCE_DT",

                    getLocalProperty("KEY_SHARED_PREFERENCE_DT"))
            }
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define wingman key in \'local.properties\' Ask your Back-END")
        }
    }
}

dependencies {
    implementation(project(BuildModules.Core.UTIL))
    implementation(project(BuildModules.Core.DOMAIN))
    implementation(project(BuildModules.Core.DATA))

    Dependencies.apply {
        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)

        // remote
        implementation(RETROFIT)
        implementation(RETROFIT_GSON)
        api(RETROFIT_COROUTINE)
        implementation(LOGGING)
        implementation(OKHTTP)

        // local
        implementation(ROOM)
        implementation(ROOM_KTX)
    }
}