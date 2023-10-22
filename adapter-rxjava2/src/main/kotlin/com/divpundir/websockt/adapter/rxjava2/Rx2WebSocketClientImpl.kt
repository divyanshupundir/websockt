package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocketClient
import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import com.divpundir.websockt.createClient
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.functions.Action
import io.reactivex.processors.PublishProcessor

internal class Rx2WebSocketClientImpl(
    factory: WebSocketFactory,
    private val scheduler: Scheduler
) : Rx2WebSocketClient {

    private val _event = PublishProcessor.create<WebSocketClient.Event>()

    private val delegate = WebSockt.createClient(
        factory = factory,
        onEvent = _event::onNext
    )

    override val event: Flowable<WebSocketClient.Event> = _event.onBackpressureDrop().share()

    override fun open(url: String): Completable = completableSubscribeOn(scheduler) {
        delegate.open(url)
    }

    override fun send(payload: String): Completable = completableSubscribeOn(scheduler) {
        delegate.send(payload)
    }

    override fun close(code: Int, reason: String?): Completable = completableSubscribeOn(scheduler) {
        delegate.close(code, reason)
    }
}

private fun completableSubscribeOn(scheduler: Scheduler, action: Action): Completable =
    Completable.fromAction(action).subscribeOn(scheduler)
