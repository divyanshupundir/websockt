package com.divpundir.websockt

public interface WebSocketFactory {

        public fun create(url: String, listener: WebSocketEvent.Listener): WebSocket
}
