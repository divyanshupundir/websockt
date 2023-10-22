package com.divpundir.websockt

public fun WebSockt.createClient(
    factory: WebSocketFactory,
    onEvent: WebSocketClient.Event.Listener
): WebSocketClient = WebSocketClientImpl(
    factory = factory,
    onEvent = onEvent
)
