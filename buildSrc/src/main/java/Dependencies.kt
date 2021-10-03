object Kotlin {
    const val VERSION = "1.3.72"
}

object RootConfig {
    const val GRADLE = "com.android.tools.build:gradle:4.0.0"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.VERSION}"
    const val DAGGER_HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:2.28-alpha"
}

object AndroidConfig {
    const val COMPILE_AND_TARGET_SDK = 29       //Q
    const val BUILD_TOOLS_VERSION = "29.0.3"
    const val APPLICATION_ID = "com.poke"
    const val MIN_SDK = 21      //Lollipop
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val TEST_INSTRUMENTATION_CUSTOM_RUNNER = "com.poke.MyCustomTestRunner"

}

object JetBrain {
    private const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Kotlin.VERSION}"
}

object Coroutines{
    private const val COROUTINE_VERSION = "1.5.1"
    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINE_VERSION"
    const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINE_VERSION"
    const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINE_VERSION"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.1.0"
    const val MAPS = "com.google.android.gms:play-services-maps:17.0.0"
}

object TestConfig {
    private const val JUNIT = "junit:junit:4.13.2"
    private const val JUNIT_VERSION = "5.7.1"

    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.1"
    const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
    const val JUNIT_JUPITER_API = "org.junit.jupiter:junit-jupiter-api:$JUNIT_VERSION"
    const val JUNIT_JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:$JUNIT_VERSION"
    const val JUNIT_JUPITER_PARAMS = "org.junit.jupiter:junit-jupiter-params:$JUNIT_VERSION"
    const val ASSERT_CORE = "org.assertj:assertj-core:3.18.0"

    const val MOCKK = "io.mockk:mockk:1.10.6"
}

object AndroidDxDependencies {
    private const val LIFECYCLE_VERSION = "2.2.0"
    private const val PAGING_VERSION = "3.0.0-alpha01"

    const val APPCOMPAT = "androidx.appcompat:appcompat:1.1.0"

    const val CORE_KTX = "androidx.core:core-ktx:1.3.0"
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"

    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:1.1.3"

    //ActivityResultContract
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.2.0-alpha06"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.3.0-alpha06"

    //Paging 3.0 core dependencies Injection
    const val PAGING = "androidx.paging:paging-runtime:$PAGING_VERSION"

}

object DaggerHilt {
    private const val HILT_VER = "2.28-alpha"
    private const val HILT_JETPACK_VER = "1.0.0-alpha01"

    //dagger-hilt
    const val ANDROID = "com.google.dagger:hilt-android:$HILT_VER"
    const val ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:$HILT_VER"
    const val LIFECYCLE_VM = "androidx.hilt:hilt-lifecycle-viewmodel:$HILT_JETPACK_VER"
    const val COMPILER = "androidx.hilt:hilt-compiler:$HILT_JETPACK_VER"
    const val TEST = "com.google.dagger:hilt-android-testing:$HILT_VER"

}

object NetworkDependencies {
    private const val RETROFIT_VERSION = "2.9.0"
    private const val MOSHI_VERSION = "1.9.2"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val GSON = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
    const val LOGGING = "com.squareup.okhttp3:logging-interceptor:4.8.0"

    const val MOSHI = "com.squareup.moshi:moshi:$MOSHI_VERSION"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:$MOSHI_VERSION"
    const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$MOSHI_VERSION"
}

object Dependencies {

    private const val LOGGER_VERSION = "2.2.0"
    private const val GLIDE_VERSION = "4.11.0"

    //Logger
    const val LOGGER = "com.orhanobut:logger:$LOGGER_VERSION"

    //Glide
    const val GLIDE = "com.github.bumptech.glide:glide:$GLIDE_VERSION"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:$GLIDE_VERSION"

    //TedPermmision
    const val TED_PERMISION = "gun0912.ted:tedpermission-rx2:2.2.3"

}

object RxJava3 {
    private const val VERSION = "3.0.0"
    const val ANDROID = "io.reactivex.rxjava3:rxandroid:$VERSION"
    // https://github.com/ReactiveX/RxAndroid
    const val JAVA = "io.reactivex.rxjava3:rxjava:$VERSION"
    // https://github.com/ReactiveX/RxJava/releases
    const val KOTLIN = "io.reactivex.rxjava3:rxkotlin:$VERSION"
    // https://github.com/ReactiveX/RxKotlin/releases
}
