package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSocketEvent
import okhttp3.WebSocketListener

public class OkHttpWebSocketListener(private val listener: WebSocketEvent.Listener) : WebSocketListener() {

    override fun onOpen(webSocket: okhttp3.WebSocket, response: okhttp3.Response) {
        listener.onEvent(WebSocketEvent.Open)
    }

    override fun onMessage(webSocket: okhttp3.WebSocket, text: String) {
        listener.onEvent(WebSocketEvent.Message(text))
    }

    override fun onClosing(webSocket: okhttp3.WebSocket, code: Int, reason: String) {
        listener.onEvent(WebSocketEvent.Closing(code, reason))
    }

    override fun onClosed(webSocket: okhttp3.WebSocket, code: Int, reason: String) {
        listener.onEvent(WebSocketEvent.Close(code, reason))
    }

    override fun onFailure(webSocket: okhttp3.WebSocket, t: Throwable, response: okhttp3.Response?) {
        listener.onEvent(WebSocketEvent.Failure(t))
    }
}
