package com.divpundir.websockt.api

public interface WebSocketFactory {

        public fun create(url: String, listener: WebSocket.Listener): WebSocket
}
