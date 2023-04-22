import com.vanniktech.maven.publish.KotlinJvm

plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish.base")
}

kotlin {
    explicitApi()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":websockt"))
    implementation(Deps.okhttp)

    testImplementation(TestDeps.Jupiter.api)
    testRuntimeOnly(TestDeps.Jupiter.engine)
}

@Suppress("UnstableApiUsage")
mavenPublishing {
    configure(KotlinJvm())
}
