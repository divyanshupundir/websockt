# adapter-rxjava2

## Usage

### Dependencies

Add the `adapter-rxjava2` dependency to your `build.gradle.kts` along with the `websockt` and engine dependencies. This
example uses the `engine-okhttp` module.

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.divpundir.websockt:websockt:$version")
    implementation("com.divpundir.websockt:engine-okhttp:$version")
    implementation("com.divpundir.websockt:adapter-rxjava2:$version")
}
```

### Creating a client

```kotlin
val factory = OkHttpWebSocktFactory(okHttpClient)

val client = Rx2WebsocktClient(factory)
```

### Opening a connection

```kotlin
// Blocking
client.open(url).blockingAwait()

// Non-blocking
client
    .open(url)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { println("Opening the connection") }
```

### Sending a message

```kotlin
// Blocking
client.send("Hello from WebSockt").blockingAwait()

// Non-blocking
client
    .send("Hello from WebSockt")
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { println("Message sent") }
```

### Receiving events

```kotlin
// Blocking
client
    .event
    .blockingSubscribe {
        when (it) {
            WebSocktClient.Event.Open -> println("Opened")
            is WebSocktClient.Event.Closing -> println("Closing")
            is WebSocktClient.Event.Close -> println("Closed")
            is WebSocktClient.Event.Failure -> println("Failed: $it.cause")
            is WebSocktClient.Event.Message.Bytes -> print("Bytes: ${it.payload}")
            is WebSocktClient.Event.Message.Text -> println("Text: ${it.payload}")
        }
    }
    
// Non-blocking
client
    .event
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe {
        when (it) {
            WebSocktClient.Event.Open -> println("Opened")
            is WebSocktClient.Event.Closing -> println("Closing")
            is WebSocktClient.Event.Close -> println("Closed")
            is WebSocktClient.Event.Failure -> println("Failed: $it.cause")
            is WebSocktClient.Event.Message.Bytes -> print("Bytes: ${it.payload}")
            is WebSocktClient.Event.Message.Text -> println("Text: ${it.payload}")
        }
    }
```

### Closing the connection

```kotlin
// Blocking
client.close().blockingAwait()

// Non-blocking
client
    .close()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { println("Closing the connection") }
```
