package com.divpundir.websockt

public interface WebSocket {

    public val url: String

    public fun send(payload: String)

    public fun close(code: Int = 1000, reason: String? = null)
}
