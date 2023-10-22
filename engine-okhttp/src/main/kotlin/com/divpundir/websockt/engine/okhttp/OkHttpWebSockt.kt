package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSockt
import okhttp3.WebSocket as DelegateSocket

internal class OkHttpWebSockt(private val socket: DelegateSocket) : WebSockt {

    override fun send(payload: String) {
        socket.send(payload)
    }

    override fun close(code: Int, reason: String?) {
        socket.close(code, reason)
    }
}
