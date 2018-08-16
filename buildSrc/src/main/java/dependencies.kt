@file:Suppress("unused", "MayBeConstant")

/**
 *
 * Where's defined all dependencies used in project modules.
 *
 * */

const val KOTLIN_VERSION = "1.2.51"
const val SUPPORT_LIBRARY_VERSION = "27.1.1"
const val CONSTRAINT_LAYOUT_VERSION = "1.1.2"
const val JUNIT_VERSION = "4.12"
const val TEST_RUNNER_VERSION = "1.0.2"
const val ESPRESSO_VERSION = "3.0.2"
const val MOCKITO_VERSION = "0.9.0"
const val TEST_RULES_VERSION = "1.0.2"
const val GROUPIE_VERSION = "2.1.0"
const val GRADLE_VERSION = "3.1.3"

object Kotlin {
    val standardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
}

object Android {
    val appCompat = "com.android.support:appcompat-v7:$SUPPORT_LIBRARY_VERSION"
    val cardView = "com.android.support:cardview-v7:$SUPPORT_LIBRARY_VERSION"
    val recyclerView = "com.android.support:recyclerview-v7:$SUPPORT_LIBRARY_VERSION"
    val constraintLayout =
        "com.android.support.constraint:constraint-layout:$CONSTRAINT_LAYOUT_VERSION"
}

object AndroidTest {
    val runner = "com.android.support.test:runner:$TEST_RUNNER_VERSION"
    val espresso = "com.android.support.test.espresso:espresso-core:$ESPRESSO_VERSION"
    val rules = "com.android.support.test:rules:$TEST_RULES_VERSION"
    val espressoIntents = "com.android.support.test.espresso:espresso-intents:$ESPRESSO_VERSION"
    val espressoContrib = "com.android.support.test.espresso:espresso-contrib:$ESPRESSO_VERSION"
}

object UnitTest {
    val jUnit = "junit:junit:$JUNIT_VERSION"
    val mockito = "com.nhaarman:mockito-kotlin:$MOCKITO_VERSION"
}

object ClasspathDependencies {
    val gradle = "com.android.tools.build:gradle:$GRADLE_VERSION"
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
}
