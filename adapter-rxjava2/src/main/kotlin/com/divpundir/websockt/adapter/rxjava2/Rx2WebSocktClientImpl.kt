package com.divpundir.websockt.adapter.rxjava2

import com.divpundir.websockt.WebSocktClient
import com.divpundir.websockt.WebSocktFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

internal class Rx2WebSocktClientImpl(
    factory: WebSocktFactory
) : Rx2WebSocktClient {

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
