package com.divpundir.websockt

public interface WebSocketFactory {

    public fun create(
        url: String,
        onFailure: WebSocket.FailureListener,
        onEvent: WebSocket.Event.Listener
    ): WebSocket
}
