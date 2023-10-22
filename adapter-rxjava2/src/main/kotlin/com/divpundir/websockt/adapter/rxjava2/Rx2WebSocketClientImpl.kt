package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocketClient
import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import com.divpundir.websockt.createClient
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

internal class Rx2WebSocketClientImpl(
    factory: WebSocketFactory
) : Rx2WebSocketClient {

    private val _event = PublishProcessor.create<WebSocketClient.Event>()

    private val delegate = WebSockt.createClient(
        factory = factory,
        onEvent = _event::onNext
    )

    override val event: Flowable<WebSocketClient.Event> = _event.onBackpressureDrop().share()

    override fun open(url: String): Completable = Completable.fromAction {
        delegate.open(url)
    }

    override fun send(payload: String): Completable = Completable.fromAction {
        delegate.send(payload)
    }

    override fun close(code: Int, reason: String?): Completable = Completable.fromAction {
        delegate.close(code, reason)
    }
}
