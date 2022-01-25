import dependencies.Dependencies
import extensions.implementation

plugins {
    with(BuildPlugins) {
        id(ANDROID_APPLICATION)
//        id(GOOGLE_SERVICE)
        id(KOTLIN_ANDROID)
        id(KOTLIN_PARCELIZE)
        id(NAVIGATION_SAFE_ARGS)
    }
}

android {

    BuildAndroidConfig.apply {

        compileSdk = COMPILE_SDK_VERSION
        defaultConfig {
            applicationId = APPLICATION_ID
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            buildToolsVersion = BUILD_TOOLS_VERSION

            versionCode = VERSION_CODE
            versionName = VERSION_NAME
        }

    }



    buildTypes {
        BuildType.apply {
            getByName(RELEASE) {
                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
            }

            getByName(DEBUG) {
                applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
                versionNameSuffix = BuildTypeDebug.versionNameSuffix
                isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
            }
        }
    }

    flavorDimensionList.add(BuildProductDimensions.ENVIRONMENT)
    productFlavors.apply {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorQA.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }

    buildFeatures {
        viewBinding = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lint {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

}

dependencies {
    implementation(project(BuildModules.Core.DATA))
    implementation(project(BuildModules.Core.DOMAIN))
    implementation(project(BuildModules.Core.DI))
    implementation(project(BuildModules.Core.UTIL))
    implementation(project(BuildModules.Core.PRESENTATION))




    implementation(project(BuildModules.Market.DI))
    implementation(project(BuildModules.Market.PRESENTATION))

    implementation(project(BuildModules.ReceiveOrder.DI))
    implementation(project(BuildModules.ReceiveOrder.PRESENTATION))

    implementation(project(BuildModules.Gallery.PRESENTATION))

    with(Dependencies) {
        // common requirement
        implementation(CORE_KTX)
        implementation(APPCOMPAT)

        // coroutine
        implementation(COROUTINES)
        implementation(COROUTINES_ANDROID)

        // common ui
        implementation(MATERIAL)
        implementation(NAVIGATION_UI)
        implementation(CONSTRAIN_LAYOUT)
        implementation(NAVIGATION_FRAGMENT)
        implementation(LIFECYCLE_SERVICE)
        implementation(LIFECYCLE_RUNTIME)
        implementation(GLIDE)

        // rx-java
        implementation(RX_ANDROID)
        implementation(RX_JAVA)

        // dependency
        implementation(KOIN_ANDROID)
        implementation(KOIN_CORE)


        // remote
        implementation(RETROFIT_GSON)

        // firebase
        implementation(FIREBASE_MESSAGING)
        implementation(FIREBASE_ANALYTICS)
        implementation(platform(FIREBASE_BOM))
        implementation(FIREBASE_DATABASE)

        // debug
        implementation(TIMBER)

        // security
        implementation(JETPACK_SECURITY)


        // google
//        implementation(GOOGLE_PLAY_SERVICES_LOCATION)
    }

}