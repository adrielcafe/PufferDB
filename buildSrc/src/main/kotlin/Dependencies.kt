object Version {
    internal const val GRADLE_ANDROID = "3.4.0-rc02"
    internal const val GRADLE_PROTOBUF = "0.8.8"

    internal const val KOTLIN = "1.3.21"
    internal const val COROUTINES = "1.1.1"

    internal const val RXJAVA = "2.2.8"
    internal const val RXANDROID = "2.1.1"

    internal const val APP_COMPAT = "1.1.0-alpha03"

    internal const val PROTOBUF_LITE = "3.0.1"
    internal const val PROTOC = "3.7.1"
    internal const val PROTOC_LITE = "3.0.0"

    internal const val TEST_JUNIT = "4.12"
    internal const val TEST_ESPRESSO = "3.1.1"
    internal const val TEST_RUNNER = "1.1.1"
}

object ProjectLib {
    const val ANDROID = "com.android.tools.build:gradle:${Version.GRADLE_ANDROID}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val PROTOBUF = "com.google.protobuf:protobuf-gradle-plugin:${Version.GRADLE_PROTOBUF}"
}

object ModuleLib {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.KOTLIN}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"

    const val RXJAVA = "io.reactivex.rxjava2:rxjava:${Version.RXJAVA}"
    const val RXANDROID = "io.reactivex.rxjava2:rxandroid:${Version.RXANDROID}"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"

    const val PROTOBUF_LITE = "com.google.protobuf:protobuf-lite:${Version.PROTOBUF_LITE}"
    const val PROTOC = "com.google.protobuf:protoc:${Version.PROTOC}"
    const val PROTOC_LITE = "com.google.protobuf:protoc-gen-javalite:${Version.PROTOC_LITE}"
}

object TestLib {
    const val JUNIT = "junit:junit:${Version.TEST_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.TEST_ESPRESSO}"
    const val RUNNER = "androidx.test:runner:${Version.TEST_RUNNER}"
}
