package com.divpundir.websockt

public interface WebSocketFactory {

        public fun create(url: String, listener: WebSocket.Listener): WebSocket
}
