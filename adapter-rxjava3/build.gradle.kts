import com.vanniktech.maven.publish.KotlinJvm

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.mavenpublish)
    alias(libs.plugins.dokka)
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(project(":websockt"))
    implementation(libs.reactivex.rxjava2)

    testImplementation(testlibs.jupiter.api)
    testRuntimeOnly(testlibs.jupiter.engine)
}

@Suppress("UnstableApiUsage")
mavenPublishing {
    configure(KotlinJvm())
}
