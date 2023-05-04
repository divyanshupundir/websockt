package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSocket
import okhttp3.Response
import okhttp3.WebSocket as DelegateSocket
import okhttp3.WebSocketListener as DelegateListener

public class OkHttpWebSocketListener(
    private val onFailure: WebSocket.FailureListener,
    private val onEvent: WebSocket.Event.Listener
) : DelegateListener() {

    override fun onOpen(webSocket: DelegateSocket, response: Response) {
        onEvent.onEvent(WebSocket.Event.Open)
    }

    override fun onMessage(webSocket: DelegateSocket, text: String) {
        onEvent.onEvent(WebSocket.Event.Message(text))
    }

    override fun onClosing(webSocket: DelegateSocket, code: Int, reason: String) {
        onEvent.onEvent(WebSocket.Event.Closing(code, reason.ifBlank { null }))
    }

    override fun onClosed(webSocket: DelegateSocket, code: Int, reason: String) {
        onEvent.onEvent(WebSocket.Event.Close(code, reason.ifBlank { null }))
    }

    override fun onFailure(webSocket: DelegateSocket, t: Throwable, response: Response?) {
        onFailure.onFailure(t)
    }
}
