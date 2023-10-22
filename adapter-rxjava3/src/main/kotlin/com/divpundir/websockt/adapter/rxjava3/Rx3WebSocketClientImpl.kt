package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocketClient
import com.divpundir.websockt.WebSocketFactory
import com.divpundir.websockt.WebSockt
import com.divpundir.websockt.createClient
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor

internal class Rx3WebSocketClientImpl(
    factory: WebSocketFactory
) : Rx3WebSocketClient {

    private val _event = PublishProcessor.create<WebSocketClient.Event>()

    private val delegate = WebSockt.createClient(
        factory = factory,
        onEvent = _event::onNext
    )

    override val event: Flowable<WebSocketClient.Event> = _event.onBackpressureBuffer().share()

    override fun open(url: String) {
        delegate.open(url)
    }

    override fun send(payload: String) {
        delegate.send(payload)
    }

    override fun close(code: Int, reason: String?) {
        delegate.close(code, reason)
    }
}
