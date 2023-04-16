import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.25.1")
    }
}

plugins {
    kotlin("jvm")
}

apply(plugin = "com.vanniktech.maven.publish.base")

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

    @Suppress("UnstableApiUsage")
    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.S01)
            signAllPublications()

            pom {
                description.set("An easier-to-use okhttp websocket client.")
                name.set(project.name)
                inceptionYear.set("2023")
                url.set("https://github.com/divyanshupundir/okhttpx-websocket/")

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                scm {
                    url.set("https://github.com/divyanshupundir/okhttpx-websocket/")
                    connection.set("scm:git:git://github.com/divyanshupundir/okhttpx-websocket.git")
                    developerConnection.set("scm:git:ssh://git@github.com/divyanshupundir/okhttpx-websocket.git")
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
