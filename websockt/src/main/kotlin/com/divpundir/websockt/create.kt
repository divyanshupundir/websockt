package com.divpundir.websockt

public fun WebSockt.createClient(
    factory: WebSocketFactory,
    onFailure: WebSocketClient.FailureListener,
    onEvent: WebSocketClient.Event.Listener
): WebSocketClient = WebSocketClientImpl(
    factory = factory,
    onFailure = onFailure,
    onEvent = onEvent
)
