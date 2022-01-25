/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dependencies

/**
 * Project dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object Dependencies {

    // COMMON
    const val ANDROID_LEGACY =
        "androidx.legacy:legacy-support-v4:${BuildDependenciesVersions.ANDROID_LEGACY}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val RECYCLE_VIEW =
        "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.RECYCLE_VIEW}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val FRAGMENT_KTX =
        "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT_KTX}"

    // TIMBER
    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"


    // MATERIAL
    const val MATERIAL_DIALOGS =
        "com.afollestad.material-dialogs:core:${BuildDependenciesVersions.MATERIAL_DIALOGS}"
    const val MATERIAL =
        "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"

    // KOTLIN COROUTINE
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"

    // ROOM
    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"


    // NAVIGATION
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI =
        "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"

    // lifecycle
    const val LIFECYCLE_SERVICE =
        "androidx.lifecycle:lifecycle-service:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.LIFECYCLE}"


    // VIEW GROUP
    const val CONSTRAIN_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAIN_LAYOUT}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"

    // PAGING
    const val PAGING = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.PAGING}"

    // RETROFIT
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_GSON =
        "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_COROUTINE =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    // LOGGING
    const val LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.LOGGING}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.OKHTTP}"


    // GOOGLE
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
    const val GOOGLE_PLAY_SERVICES_LOCATION =
        "com.google.android.gms:play-services-location:${BuildDependenciesVersions.GOOGLE_PLAY_SERVICES_LOCATION}"


    // LAYOUT SUPPORT
    const val SHIMMER = "com.facebook.shimmer:shimmer:${BuildDependenciesVersions.SHIMMER}"
    const val GLIDE = "com.github.bumptech.glide:glide:${BuildDependenciesVersions.GLIDE}"
    const val LOTTIE = "com.airbnb.android:lottie:${BuildDependenciesVersions.LOTTIE}"

    // CAMERA-X
    const val CAMERAX =
        "androidx.camera:camera-camera2:${BuildDependenciesVersions.CAMERAX_VERSION}"
    const val CAMERAX_LIFECYCLE =
        "androidx.camera:camera-lifecycle:${BuildDependenciesVersions.CAMERAX_VERSION}"
    const val CAMERAX_VIEW = "androidx.camera:camera-view:${BuildDependenciesVersions.CAMERAX_VIEW}"

    // RX-JAVA
    const val RX_JAVA = "io.reactivex.rxjava2:rxjava:${BuildDependenciesVersions.RX_JAVA}"
    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${BuildDependenciesVersions.RX_ANDROID}"

    // KOIN
    const val KOIN_CORE = "io.insert-koin:koin-core:${BuildDependenciesVersions.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${BuildDependenciesVersions.KOIN}"

    // FIREBASE
    const val FIREBASE_DATABASE =
        "com.google.firebase:firebase-database:${BuildDependenciesVersions.FIREBASE_DATABASE}"
    const val FIREBASE_BOM =
        "com.google.firebase:firebase-bom:${BuildDependenciesVersions.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_MESSAGING =
        "com.google.firebase:firebase-messaging-ktx"

    // SECURITY
    const val  JETPACK_SECURITY ="androidx.security:security-crypto:${BuildDependenciesVersions.JETPACK_SECURITY}"
}
