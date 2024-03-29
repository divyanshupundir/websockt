pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("testlibs") {
            from(files("gradle/testlibs.versions.toml"))
        }
    }
}

rootProject.name = "websockt-root"

include("websockt")
include("engine-okhttp")
include("adapter-rxjava2")
include("adapter-rxjava3")
include("adapter-coroutines")
