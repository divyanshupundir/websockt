package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocketClient
import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt

public fun WebSockt.createRx3Client(
    factory: WebSocketFactory,
    onFailure: WebSocketClient.FailureListener,
): Rx3WebSocketClient = Rx3WebSocketClientImpl(
    factory,
    onFailure
)
