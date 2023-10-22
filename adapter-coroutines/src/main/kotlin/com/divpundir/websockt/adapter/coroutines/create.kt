package com.divpundir.websockt.adapter.coroutines

import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import kotlinx.coroutines.CoroutineScope

public fun WebSockt.createCoroutinesClient(
    factory: WebSocketFactory,
    scope: CoroutineScope
): CoroutinesWebSocketClient = CoroutinesWebSocketClientImpl(
    factory,
    scope
)
