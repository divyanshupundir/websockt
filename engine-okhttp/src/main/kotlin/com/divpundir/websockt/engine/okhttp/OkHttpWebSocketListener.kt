package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSocket
import okhttp3.Response
import okio.ByteString
import okhttp3.WebSocket as DelegateSocket
import okhttp3.WebSocketListener as DelegateListener

internal class OkHttpWebSocketListener(
    private val onEvent: WebSocket.Event.Listener,
) : DelegateListener() {

    override fun onOpen(webSocket: DelegateSocket, response: Response) {
        onEvent.onEvent(WebSocket.Event.Open)
    }

    override fun onMessage(webSocket: DelegateSocket, text: String) {
        onEvent.onEvent(WebSocket.Event.Message.Text(text))
    }

    override fun onMessage(webSocket: DelegateSocket, bytes: ByteString) {
        onEvent.onEvent(WebSocket.Event.Message.Bytes(bytes.toByteArray()))
    }

    override fun onClosing(webSocket: DelegateSocket, code: Int, reason: String) {
        onEvent.onEvent(WebSocket.Event.Closing(code, reason.ifBlank { null }))
    }

    override fun onClosed(webSocket: DelegateSocket, code: Int, reason: String) {
        onEvent.onEvent(WebSocket.Event.Close(code, reason.ifBlank { null }))
    }

    override fun onFailure(webSocket: DelegateSocket, t: Throwable, response: Response?) {
        onEvent.onEvent(WebSocket.Event.Failure(t))
    }
}
