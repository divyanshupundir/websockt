package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

public fun WebSockt.createRx3Client(
    factory: WebSocketFactory,
    scheduler: Scheduler = Schedulers.io()
): Rx3WebSocketClient = Rx3WebSocketClientImpl(
    factory,
    scheduler
)
