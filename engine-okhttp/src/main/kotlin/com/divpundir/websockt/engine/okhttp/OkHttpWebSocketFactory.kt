package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSocket
import com.divpundir.websockt.WebSocketEvent
import com.divpundir.websockt.WebSocketFactory
import okhttp3.OkHttpClient
import okhttp3.Request

public class OkHttpWebSocketFactory(
    private val client: OkHttpClient
) : WebSocketFactory {

    override fun create(url: String, listener: WebSocketEvent.Listener): WebSocket {
        val request = Request.Builder()
            .url(url)
            .build()

        val socket = client.newWebSocket(request, OkHttpWebSocketListener(listener))
        return OkHttpWebSocket(socket)
    }
}
