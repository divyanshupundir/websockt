package com.divpundir.websockt.adapter.rxjava3

import com.divpundir.websockt.WebSocktClient
import com.divpundir.websockt.WebSocktFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor

internal class Rx3WebSocktClientImpl(
    factory: WebSocktFactory,
) : Rx3WebSocktClient {

    private val _event = PublishProcessor.create<WebSocktClient.Event>()

    private val delegate = WebSocktClient(
        factory = factory,
        onEvent = _event::onNext
    )

    override val event: Flowable<WebSocktClient.Event> = _event.onBackpressureDrop().share()

    override fun open(url: String): Completable = Completable.fromAction {
        delegate.open(url)
    }

    override fun send(payload: String): Completable = Completable.fromAction {
        delegate.send(payload)
    }

    override fun send(payload: ByteArray): Completable = Completable.fromAction {
        delegate.send(payload)
    }

    override fun close(code: Int, reason: String?): Completable = Completable.fromAction {
        delegate.close(code, reason)
    }
}
