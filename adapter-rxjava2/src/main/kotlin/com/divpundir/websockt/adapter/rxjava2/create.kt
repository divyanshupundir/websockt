package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt

public fun WebSockt.createRx2Client(
    factory: WebSocketFactory,
): Rx2WebSocketClient = Rx2WebSocketClientImpl(
    factory
)
