object Versions {
    const val COMPILE_SDK_VERSION = 30
    const val BUILD_TOOLS_VERSION = "30.0.2"
    const val MIN_SDK_VERSION = 22
    const val TARGET_SDK_VERSION = 30
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val CORE_KTX_VERSION = "1.3.2"
    const val FRAGMENT_KTX = "1.2.5"
    const val APPCOMPAT_VERSION = "1.2.0"
    const val MATERIAL_VERSION = "1.2.1"
    const val CONSTRAINT_LAYOUT_VERSION = "2.0.4"
    const val JUNIT_VERSION = "4.13.1"
    const val JUNIT_KTX_VERSION = "1.1.2"
    const val TEST_CORE = "1.3.0"
    const val EXT_JUNIT_VERSION = "1.1.2"
    const val ESPRESSO_CORE_VERSION = "3.3.0"
    const val HILT_ANDROID = "2.28-alpha"
    const val HILT = "1.0.0-alpha01"
    const val RETROFIT = "2.9.0"
    const val PICASSO = "2.71828"
    const val RXJAVA = "2.2.20"
    const val RXANDROID = "2.1.1"
    const val NAV = "2.3.2"
    const val LEGACY_SUPPORT = "1.0.0"
    const val LIFE_CYCLE = "2.2.0"
    const val OK_HTTP = "4.7.2"
    const val MOSHI = "1.9.3"
    const val MOSHI_CODEGEN = "1.9.2"
    const val TRUTH = "1.0.1"
    const val PAGING = "3.0.0-alpha11"
}

object CoreDependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val RXJAVA = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA}"
    const val RXANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RXANDROID}"
    const val NAV = "androidx.navigation:navigation-fragment-ktx:${Versions.NAV}"
    const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAV}"
    const val SAFE_ARGS_PLUGIN =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAV}"
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.LEGACY_SUPPORT}"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFE_CYCLE}"
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE}"
    const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.JUNIT_KTX_VERSION}"
    const val TEST_CORE = "androidx.test:core-ktx:${Versions.TEST_CORE}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT_VERSION}"
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE_VERSION}"
    const val TRUTH = "com.google.truth:truth:${Versions.TRUTH}"
}

object Dagger {
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT_ANDROID}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_ANDROID}"
    const val HILT_LIFECYCLE = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT}"

    const val HILT_INSTRUMENTATION_TESTING =
        "com.google.dagger:hilt-android-testing:${Versions.HILT_ANDROID}"
    const val HILT_INSTRUMENTATION_TESTING_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Versions.HILT_ANDROID}"
    const val HILT_UNIT_TEST = "com.google.dagger:hilt-android-testing:${Versions.HILT_ANDROID}"
    const val HILT_UNIT_TEST_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_ANDROID}"
    const val HILT_GRADLE_PLUGIN =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_ANDROID}"
}

object Network {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP}"
    const val RETROFIT_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}"
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI_CODEGEN}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
}
