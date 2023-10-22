package com.divpundir.websockt.engine.okhttp

import com.divpundir.websockt.WebSockt
import okio.ByteString.Companion.toByteString
import okhttp3.WebSocket as DelegateSocket

internal class OkHttpWebSockt(private val socket: DelegateSocket) : WebSockt {

    override fun send(payload: String) {
        socket.send(payload)
    }

    override fun send(payload: ByteArray) {
        socket.send(payload.toByteString())
    }

    override fun close(code: Int, reason: String?) {
        socket.close(code, reason)
    }
}
