package com.divpundir.websockt

public interface WebSocketFactory {

    public fun create(
        url: String,
        onEvent: WebSocket.Event.Listener
    ): WebSocket
}
