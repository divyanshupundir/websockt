# adapter-coroutines

## Usage

### Dependencies

Add the `adapter-coroutines` dependency to your `build.gradle.kts` along with the `websockt` and engine dependencies.
This example uses the `engine-okhttp` module.

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.divpundir.websockt:websockt:$version")
    implementation("com.divpundir.websockt:engine-okhttp:$version")
    implementation("com.divpundir.websockt:adapter-coroutines:$version")
}
```

### Creating a client

```kotlin
val factory = OkHttpWebSocktFactory(okHttpClient)

val client = CoroutinesWebsocktClient(factory, viewModelScope)
```

### Opening a connection

```kotlin
viewModelScope.launch(Dispatchers.IO) {
    client.open(url)
    println("Opening the connection")
}
```

### Sending a message

```kotlin
viewModelScope.launch(Dispatchers.IO) {
    client.send("Hello from WebSockt")
    println("Message sent")
}
```

### Receiving events

```kotlin
client
    .event
    .onEach {
        when (it) {
            WebSocktClient.Event.Open -> println("Opened")
            is WebSocktClient.Event.Closing -> println("Closing")
            is WebSocktClient.Event.Close -> println("Closed")
            is WebSocktClient.Event.Failure -> println("Failed: $it.cause")
            is WebSocktClient.Event.Message.Bytes -> print("Bytes: ${it.payload}")
            is WebSocktClient.Event.Message.Text -> println("Text: ${it.payload}")
        } 
    }
    .launchIn(viewModelScope)
```

### Closing the connection

```kotlin
viewModelScope.launch(Dispatchers.IO) {
    client.close()
    println("Closing the connection")
}
```
