package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSocket
import okhttp3.WebSocket as DelegateSocket

internal class OkHttpWebSocket(private val socket: DelegateSocket) : WebSocket {

    override fun send(payload: String) {
        socket.send(payload)
    }

    override fun close(code: Int, reason: String?) {
        socket.close(code, reason)
    }
}
