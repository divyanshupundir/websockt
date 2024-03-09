# Websockt

[![License](https://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg?maxAge=2592000)](https://github.com/divyanshupundir/websockt/blob/main/LICENSE.md)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.22-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Maven Central](https://img.shields.io/maven-central/v/com.divpundir.websockt/websockt)](https://central.sonatype.com/namespace/com.divpundir.websockt)

Websockt is a simple WebSocket client library for Kotlin.

## Usage

### Dependencies

The latest artifacts are available on [Maven Central](https://central.sonatype.com/namespace/com.divpundir.websockt).

Add the `websockt` dependency to your `build.gradle.kts`.

```kotlin
implementation("com.divpundir.websockt:websockt:$version")
```

Add one of the available websocket engines to the dependencies. This example uses the OkHttp engine.

```kotlin
implementation("com.divpundir.websockt:engine-okhttp:$version")
```

Pick an adapter of your choice and add it to the dependencies.

```kotlin
implementation("com.divpundir.websockt:adapter-rxjava2:$version")
```

Refer the documentation of the different adapters to learn how to use them.
