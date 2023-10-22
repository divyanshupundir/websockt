package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSocket
import com.divpundir.websockt.WebSocketFactory
import okhttp3.OkHttpClient
import okhttp3.Request

public class OkHttpWebSocketFactory(
    private val httpClient: OkHttpClient
) : WebSocketFactory {

    override fun create(
        url: String,
        onEvent: WebSocket.Event.Listener
    ): WebSocket {
        val request = Request.Builder()
            .url(url)
            .build()

        val socket = httpClient.newWebSocket(
            request,
            OkHttpWebSocketListener(onEvent)
        )

        return OkHttpWebSocket(socket)
    }
}
