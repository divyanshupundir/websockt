# Websockt

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
