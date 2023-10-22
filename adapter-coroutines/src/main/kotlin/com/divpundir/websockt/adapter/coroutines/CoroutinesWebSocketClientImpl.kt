package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocketClient
import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import com.divpundir.websockt.createClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class CoroutinesWebSocketClientImpl(
    factory: WebSocketFactory,
    private val scope: CoroutineScope
) : CoroutinesWebSocketClient {

    private val _event = MutableSharedFlow<WebSocketClient.Event>(
        extraBufferCapacity = 128,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val delegate = WebSockt.createClient(
        factory = factory,
        onEvent = { scope.launch { _event.emit(it) } }
    )

    override val event: SharedFlow<WebSocketClient.Event> = _event.asSharedFlow()

    override suspend fun open(url: String) {
        delegate.open(url)
    }

    override suspend fun send(payload: String) {
        delegate.send(payload)
    }

    override suspend fun close(code: Int, reason: String?) {
        delegate.close(code, reason)
    }
}