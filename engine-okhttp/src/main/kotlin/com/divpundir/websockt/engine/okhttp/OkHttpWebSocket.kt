package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.api.WebSocket
import okhttp3.Response
import okhttp3.WebSocketListener
import okhttp3.WebSocket as DelegateSocket

internal class OkHttpWebSocket(private val socket: DelegateSocket) : WebSocket {

    override val url: String = socket.request().url.toString()

    override fun send(payload: String) {
        socket.send(payload)
    }

    override fun close(code: Int, reason: String?) {
        socket.close(code, reason)
    }

    class Listener(private val listener: WebSocket.Listener) : WebSocketListener() {

        override fun onOpen(webSocket: DelegateSocket, response: Response) {
            listener.onEvent(WebSocket.Event.Open)
        }

        override fun onMessage(webSocket: DelegateSocket, text: String) {
            listener.onEvent(WebSocket.Event.Message(text))
        }

        override fun onClosing(webSocket: DelegateSocket, code: Int, reason: String) {
            listener.onEvent(WebSocket.Event.Closing(code, reason))
        }

        override fun onClosed(webSocket: DelegateSocket, code: Int, reason: String) {
            listener.onEvent(WebSocket.Event.Close(code, reason))
        }

        override fun onFailure(webSocket: DelegateSocket, t: Throwable, response: Response?) {
            listener.onEvent(WebSocket.Event.Failure(t))
        }
    }
}
