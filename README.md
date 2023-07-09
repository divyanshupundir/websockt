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

Pick an adapter of your choice and add it to the dependencies. This example uses the RxJava 2 adapter.

```kotlin
implementation("com.divpundir.websockt:adapter-rxjava2:$version")
```

### Create a WebSocketFactory

```kotlin
val factory = OkHttpWebsSocketFactory(yourOkHttpClient)
```

### Create a WebSocketClient

```kotlin
val client = WebSockt.createRx2Client(factory) { c: WebSocketCLient, t: Throwable ->
    // Handle websocket failure and reconnect
}
```

### Open a connection

```kotlin
client.open(url)
```

### Receive messages

```kotlin
// Blocking
client
    .event
    .blockingSubscribe { println("Received: $it") }

// Non-blocking
client
    .event
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        { println("Received: $it") },
        Throwable::printStackTrace
    )
```

### Send messages

```kotlin
client.send("Hello there!")
```

### Close the connection

```kotlin
client.close()
```

### Reconnect on failure

Use the `onFailure` block while creating the `WebSocketClient`

```kotlin
val client = WebSockt.createRx2Client(factory) { c: WebSocketCLient, t: Throwable ->
    Thread.sleep(1000)
    c.open(url) // Or use a new url
}
```
