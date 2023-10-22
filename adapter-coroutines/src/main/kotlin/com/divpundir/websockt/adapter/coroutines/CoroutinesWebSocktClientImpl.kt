package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocktClient
import com.divpundir.websockt.WebSocktFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class CoroutinesWebSocktClientImpl(
    factory: WebSocktFactory,
    private val scope: CoroutineScope
) : CoroutinesWebSocktClient {

    private val _event = MutableSharedFlow<WebSocktClient.Event>(
        extraBufferCapacity = 128,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val delegate = WebSocktClient(
        factory = factory,
        onEvent = { scope.launch { _event.emit(it) } }
    )

    override val event: SharedFlow<WebSocktClient.Event> = _event.asSharedFlow()

    override suspend fun open(url: String) {
        delegate.open(url)
    }

    override suspend fun send(payload: String) {
        delegate.send(payload)
    }

    override suspend fun send(payload: ByteArray) {
        delegate.send(payload)
    }

    override suspend fun close(code: Int, reason: String?) {
        delegate.close(code, reason)
    }
}