package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocketClient
import kotlinx.coroutines.flow.SharedFlow

public interface CoroutinesWebSocketClient {

    public val event: SharedFlow<WebSocketClient.Event>

    public suspend fun open(url: String)

    public suspend fun send(payload: String)

    public suspend fun close(code: Int = 1000, reason: String? = null)
}
