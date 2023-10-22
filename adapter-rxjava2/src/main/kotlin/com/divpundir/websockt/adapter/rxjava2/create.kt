package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

public fun WebSockt.createRx2Client(
    factory: WebSocketFactory,
    scheduler: Scheduler = Schedulers.io()
): Rx2WebSocketClient = Rx2WebSocketClientImpl(
    factory,
    scheduler
)
