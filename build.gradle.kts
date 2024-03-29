import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.mavenpublish)
    alias(libs.plugins.dokka)
}

allprojects {
    group = Config.group
    version = Config.version

    tasks.withType<JavaCompile> {
        sourceCompatibility = Config.javaVersion.toString()
        targetCompatibility = Config.javaVersion.toString()
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Config.javaVersion.toString()
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    @Suppress("UnstableApiUsage")
    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.S01)
            signAllPublications()

            pom {
                description.set("An easier-to-use okhttp websocket client.")
                name.set(project.name)
                inceptionYear.set("2023")
                url.set("https://github.com/divyanshupundir/websockt/")

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                scm {
                    url.set("https://github.com/divyanshupundir/websockt/")
                    connection.set("scm:git:git://github.com/divyanshupundir/websockt.git")
                    developerConnection.set("scm:git:ssh://git@github.com/divyanshupundir/websockt.git")
                }

                developers {
                    developer {
                        id.set("divyanshupundir")
                        name.set("Divyanshu Pundir")
                        url.set("https://github.com/divyanshupundir/")
                    }
                }
            }
        }
    }
}

task("publishAll") {
    dependsOn(
        ":websockt:publish",
        ":engine-okhttp:publish",
        ":adapter-rxjava2:publish",
        ":adapter-rxjava3:publish",
        ":adapter-coroutines:publish"
    )
}

task("closeAndReleaseAll") {
    dependsOn(
        ":websockt:closeAndReleaseRepository",
        ":engine-okhttp:closeAndReleaseRepository",
        ":adapter-rxjava2:closeAndReleaseRepository",
        ":adapter-rxjava3:closeAndReleaseRepository",
        ":adapter-coroutines:closeAndReleaseRepository"
    )
}

task("createGitTag") {
    doLast {
        val tagName = "v${Config.version}"
        exec { commandLine("git", "tag", tagName) }
        exec { commandLine("git", "push", "origin", tagName) }
    }
}
